package com.jovan.wxjava.controller;

import com.jovan.wxjava.entity.RestResult;
import com.jovan.wxjava.entity.RestResultGenerator;
import com.jovan.wxjava.protocol.LoginProtocol;
import com.jovan.wxjava.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Jovan
 * @create 2019/8/12
 */
@RestController
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    /**
     * 获取微信登陆二维码地址
     * @return
     */
    @GetMapping("/getQRCodeUrl")
    public RestResult getQRCodeUrl() {
        return RestResultGenerator.createOkResult(weChatService.getQRCodeUrl());
    }

    /**
     * 微信扫码回调处理
     * 使用 @Valid + BindingResult 进行 controller 参数校验，实现断路器。大家可以根据自己的喜好来，不必跟我这样做
     * @param input
     * @param bindingResult
     * @return
     */
    @GetMapping("/wxCallBack")
    public String wxCallBack(@RequestBody @Valid LoginProtocol.WeChatQrCodeCallBack.Input input, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "failedPage";
        }

        if (weChatService.wxCallBack(input)) {
            return "successPage";
        } else {
            return "failedPage";
        }
    }
}
