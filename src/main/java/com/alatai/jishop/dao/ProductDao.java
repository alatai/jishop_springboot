package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductDao
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 13:33
 */
public interface ProductDao extends JpaRepository<Product, Integer> {

    Page<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findByCategory(Category category);

    Page<Product> findByNameLike(String keyword, Pageable pageable);
}
