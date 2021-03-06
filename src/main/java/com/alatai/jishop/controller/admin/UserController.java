package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.UserService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 10:36
 */
@RestController("userController")
@RequestMapping("/admin/data")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public PageResult<User> list(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size) {
        start = start < 0 ? 0 : start;

        return userService.findAll(start, size, 5);
    }
}
