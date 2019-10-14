package com.wg.base.backend.service;

import com.querydsl.core.QueryResults;
import com.wg.base.backend.common.page.BasePageable;
import com.wg.base.backend.controller.bean.CustomerAddBean;
import com.wg.base.backend.controller.bean.CustomerUpdateBean;
import com.wg.base.backend.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(CustomerAddBean customerAddBean);

    Customer getCustomerById(Long id);

    String getLogin(String userName,String password);

    List<Customer> getCustomerAll();

    QueryResults<Customer> getCustomerByPage(BasePageable basePageable, String customerName, Integer ageStart, Integer ageEnd);

    Customer updateCustomer(CustomerUpdateBean customerUpdateBean);

    void deleteCustomer(Long id);
}
