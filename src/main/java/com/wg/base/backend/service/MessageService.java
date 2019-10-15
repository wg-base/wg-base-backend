package com.wg.base.backend.service;

public interface MessageService {

    boolean checkCanSend(String phone);

    void sendMessage(String phone,String msg);
}
