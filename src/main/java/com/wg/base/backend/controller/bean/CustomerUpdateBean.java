package com.wg.base.backend.controller.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "更新用户")
public class CustomerUpdateBean extends CustomerAddBean{

    @ApiModelProperty(value = "ID")
    private Long id;

}
