package com.wg.base.backend.controller;

import com.wg.base.backend.common.Constant;
import com.wg.base.backend.common.exception.LogicException;
import com.wg.base.backend.common.result.Result;
import com.wg.base.backend.common.result.ResultMessage;
import com.wg.base.backend.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "信息", tags = { "信息" })
@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping()
    @ApiOperation(value="发送信息", notes="发送信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String", name ="access_token", value ="token",required = true),
            @ApiImplicitParam(paramType = "query",dataType = "String", name ="phone", value ="手机号",required = true),
            @ApiImplicitParam(paramType = "query",dataType = "String", name ="msg", value ="信息内容",required = true)
    })
    public Result<String> sendMessage(String phone,String msg){
        boolean flag = messageService.checkCanSend(phone);
        checkPhoneAndMsg(phone,msg,flag);
        messageService.sendMessage(phone,msg);
        return Result.success(null);
    }

    private void checkPhoneAndMsg(String phone,String msg,boolean flag){
        if(StringUtils.isBlank(phone)){
            throw new LogicException(ResultMessage.MOBILE_EMPTY);
        }
        if(!phone.matches(Constant.REGEX_MOBILE)){
            throw new LogicException(ResultMessage.MOBILE_ERROR);
        }
        if(!flag){
            throw new LogicException(ResultMessage.MOBILE_FREQUENTLY_ERROR);
        }
        if(StringUtils.isBlank(msg)){
            throw new LogicException(ResultMessage.MESSAGE_EMPTY_ERROR);
        }

    }
}
