package com.huuu.web.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 令牌响应数据
 * @author huuu
 */
@Getter
@Setter
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = -6554229479491524764L;

    /** 令牌*/
    private String token;
}
