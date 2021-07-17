package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.OrderItemService;
import com.alatai.jishop.service.OrderService;
import com.alatai.jishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/17 16:27
 */
@RestController("shopOrderController")
@RequestMapping("/data")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;

    @GetMapping("/buyCurrently")
    public int buyCurrently(HttpSession session, Integer pid, Integer num) {
        System.out.println("pid=" + pid);
        System.out.println("num=" + num);

        User user = (User) session.getAttribute("user");

        return orderItemService.checkOrderItem(user, pid, num);
    }

    @GetMapping("/submitOrder")
    public Map<String, Object> submitOrder(HttpSession session, String[] oiId) {
        List<OrderItem> orderItems = new ArrayList<>();
        float amount = 0;

        for (String id : oiId) {
            OrderItem orderItem = orderItemService.findById(Integer.parseInt(id));
            amount += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();

            orderItems.add(orderItem);
        }

        productService.associateOrderItem(orderItems);
        session.setAttribute("orderItems", orderItems);

        Map<String, Object> result = new HashMap<>();
        result.put("amount", amount);
        result.put("orderItems", orderItems);

        return result;
    }

}