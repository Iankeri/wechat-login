package com.jovan.wxjava.config;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jovan
 * @create 2019/8/5
 */
@Configuration
@Data
public class WxOpenConfig {
    /**
     * 设置云视讯的appid
     */
    @Value("${wx.open.config.appid}")
    private String appid;

    /**
     * 设置云视讯的app secret
     */
    @Value("${wx.open.config.secret}")
    private String secret;

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();

        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(appid);
        configStorage.setSecret(secret);

        service.setWxMpConfigStorage(configStorage);
        return service;
    }
}
