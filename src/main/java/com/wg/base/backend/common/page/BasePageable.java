package com.wg.base.backend.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BasePageable {

    @ApiModelProperty("页码(从1开始,默认1)")
    private int pageNumber = 1;
    @ApiModelProperty("每页显示最大条目数,默认10")
    private int pageSize = 10;
}
