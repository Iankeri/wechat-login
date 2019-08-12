package com.jovan.wxjava.service.impl;

import com.jovan.wxjava.constant.Constant;
import com.jovan.wxjava.protocol.LoginProtocol;
import com.jovan.wxjava.service.WeChatService;
import com.jovan.wxjava.util.DateUtil;
import com.jovan.wxjava.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Jovan
 * @create 2019/8/12
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private WxMpService wxMpService;

    @Value("${wx.open.config.redirectUrl}")
    private String wxRedirectUrl;

    @Value("${wx.open.config.csrfKey}")
    private String CSRF_KEY;

    @Override
    public String getQRCodeUrl() {
        // 生成 state 参数，用于防止 csrf
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String state = MD5Utils.generate(CSRF_KEY + date);
        return wxMpService.buildQrConnectUrl(wxRedirectUrl, Constant.WeChatLogin.SCOPE, state);
    }

    @Override
    public Boolean wxCallBack(LoginProtocol.WeChatQrCodeCallBack.Input input) {

        String code = input.getCode();
        String state = input.getState();
        String openid = null;
        String token = null;

        if (code == null) {
            return false;
        }

        if (code != null && state != null) {
            // 验证 state,防止跨站请求伪造攻击
            String date = DateUtil.format(new Date(), "yyyyMMdd");
            Boolean isNotCsrf = MD5Utils.verify(CSRF_KEY + date, state);
            if (!isNotCsrf) {
                return false;
            }

            // 获取 openid
            try {

                WxMpOAuth2AccessToken accessToken =wxMpService.oauth2getAccessToken(code);
                openid = accessToken.getOpenId();
                token = accessToken.getAccessToken();


                // 拿到 openid 后做自己的业务, 获取用 token 进一步获取用户信息

                // 用 access_token 获取用户的信息
                WxMpUser user = wxMpService.oauth2getUserInfo(accessToken, null);

            } catch (WxErrorException e) {
                log.error(e.getMessage(), e);
            }

            return true;
        }
        return false;
    }
}
