package com.huuu.web.common.request;

import com.huuu.base.request.BaseRequest;
import com.huuu.base.validation.constraints.Mobile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 用户信息更新数据
 * @author huuu
 */
@Getter
@Setter
public class UpdateUserInfoRequest extends BaseRequest {
    private static final long serialVersionUID = 2014214960641300121L;

    /** 姓名*/
    @NotBlank(message = "姓名不能为空")
    private String name;
    /** 头像*/
    private String avatar;
    /** 性别：0-未知 1-男 2-女*/
    @NotNull(message = "性别不能为空")
    private Integer gender;
    /** 手机号*/
    @NotBlank(message = "手机号不能为空")
    @Mobile
    private String mobile;
    /** 邮箱*/
    @NotBlank(message = "邮箱为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /** 生日*/
    private LocalDate birthday;
}
