package com.wg.base.backend.controller.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "添加用户")
public class CustomerAddBean {

    @ApiModelProperty(value = "用户名称")
    private String customerName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "电话")
    private String phone;
}
