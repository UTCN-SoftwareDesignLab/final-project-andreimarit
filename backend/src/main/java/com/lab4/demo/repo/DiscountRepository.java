package com.lab4.demo.repo;

import com.lab4.demo.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount,Long> {

    Discount findDiscountByPercentLike(Long percent);
}
