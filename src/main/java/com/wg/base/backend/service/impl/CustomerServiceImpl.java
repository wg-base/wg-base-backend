package com.wg.base.backend.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wg.base.backend.common.page.BasePageable;
import com.wg.base.backend.controller.bean.CustomerAddBean;
import com.wg.base.backend.controller.bean.CustomerUpdateBean;
import com.wg.base.backend.dao.CustomerRepository;
import com.wg.base.backend.domain.Customer;
import com.wg.base.backend.domain.QCustomer;
import com.wg.base.backend.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Customer addCustomer(CustomerAddBean customerAddBean) {
        Customer customer = new Customer(customerAddBean);
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public List<Customer> getCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public QueryResults<Customer> getCustomerByPage(BasePageable basePageable, String customerName, Integer ageStart, Integer ageEnd) {
        QCustomer customer = QCustomer.customer;
        Predicate predicate = customer.isNotNull().or(customer.isNull());
        predicate = StringUtils.isBlank(customerName) ? predicate : ExpressionUtils.and(predicate,customer.customerName.like("%"+customerName+"%"));
        predicate =ageStart==null ? predicate : ExpressionUtils.and(predicate,customer.age.gt(ageStart));
        predicate = ageEnd==null ? predicate : ExpressionUtils.and(predicate,customer.age.lt(ageEnd));
        QueryResults<Customer> queryResults = jpaQueryFactory.selectFrom(customer)
                .where(predicate)
                .offset((basePageable.getPageNumber()-1)*basePageable.getPageSize())
                .limit(basePageable.getPageSize())
                .orderBy(customer.id.desc())
                .fetchResults();
        return queryResults;
    }

    @Override
    public Customer updateCustomer(CustomerUpdateBean customerUpdateBean) {
        Customer customer = customerRepository.getOne(customerUpdateBean.getId());
        customer.setCustomerName(customerUpdateBean.getCustomerName());
        customer.setPassword(customerUpdateBean.getPassword());
        customer.setAge(customerUpdateBean.getAge());
        customer.setPhone(customerUpdateBean.getPhone());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
