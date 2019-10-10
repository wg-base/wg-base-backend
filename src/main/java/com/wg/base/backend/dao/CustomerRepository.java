package com.wg.base.backend.dao;

import com.wg.base.backend.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerRepository extends JpaRepository<Customer,Long>,
        QuerydslPredicateExecutor<Customer> {
}
