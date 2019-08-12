package com.jovan.wxjava.service;

import com.jovan.wxjava.protocol.LoginProtocol;

/**
 * @author Jovan
 * @create 2019/8/12
 */
public interface WeChatService {
    /**
     * 微信扫码登录请求地址的生成
     * @return
     */
    String getQRCodeUrl();

    /**
     * 微信扫码后的回调处理
     * @param input
     * @return
     */
    Boolean wxCallBack(LoginProtocol.WeChatQrCodeCallBack.Input input);
}
