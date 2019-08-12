package com.jovan.wxjava.protocol;

import com.jovan.wxjava.message.Message;

import javax.validation.constraints.NotBlank;

/**
 * @author Jovan
 * @create 2019/8/12
 */
public interface LoginProtocol {
    interface WeChatQrCodeCallBack {
        class Input {

            /**
             * 授权临时票据，用户确认授权不为空，取消授权则为空
             */
            private String code;

            @NotBlank(message = Message.WeChatQrCode.STATE_NOT_NULL)
            private String state;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}
