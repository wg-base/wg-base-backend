package com.wg.base.backend.controller.bean;

import com.wg.base.backend.common.page.BasePageable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户条件查询")
public class CustomerPageBean extends BasePageable {

    @ApiModelProperty("用户名")
    private String customerName;

    @ApiModelProperty("年龄范围")
    private Integer ageStart;

    @ApiModelProperty("年龄范围")
    private Integer ageEnd;

}
