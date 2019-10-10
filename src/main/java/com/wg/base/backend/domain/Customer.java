package com.wg.base.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wg.base.backend.controller.bean.CustomerAddBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_customer")
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel("用户信息")
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column
    @ApiModelProperty(value = "用户名称")
    private String customerName;

    @Column
    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;

    @Column
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Column
    @ApiModelProperty(value = "电话")
    private String phone;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Customer() {}

    public Customer(CustomerAddBean customerAddBean) {
        this.customerName = customerAddBean.getCustomerName();
        this.password = customerAddBean.getPassword();
        this.age = customerAddBean.getAge();
        this.phone = customerAddBean.getPhone();
    }
}
