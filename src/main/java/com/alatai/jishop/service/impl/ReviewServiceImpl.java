package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.ReviewDao;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Review;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ReviewService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/17 10:53
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Override
    public List<Review> findByProduct(Product product) {
        return reviewDao.findByProduct(product);
    }

    @Override
    public Review findById(Integer id) {
        return reviewDao.getById(id);
    }

    @Override
    public Review insert(Review review) {
        return reviewDao.save(review);
    }

    @Override
    public Review update(Review review) {
        return reviewDao.save(review);
    }

    @Override
    public void deleteById(Integer id) {
        reviewDao.deleteById(id);
    }

    @Override
    public int getReviewCount(Product product) {
        return reviewDao.countByProduct(product);
    }

    @Override
    public void insertReviews(List<Review> reviews, User user) {
        for (Review review : reviews) {
            review.setUser(user);
            review.setCreatedDate(new Date());

            insert(review);
        }
    }
}
