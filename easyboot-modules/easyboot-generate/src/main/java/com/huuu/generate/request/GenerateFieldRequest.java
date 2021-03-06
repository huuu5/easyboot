package com.huuu.generate.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author huuu
 */
@Getter
@Setter
@Validated
public class GenerateFieldRequest extends BaseRequest {
    private static final long serialVersionUID = -3752721360126578947L;

    /** 列名称*/
    @NotBlank(message = "列名称不能为空")
    private String columnName;
    /** 字段名称*/
    @NotBlank(message = "字段名称不能为空")
    private String fieldName;
    /** 数据类型*/
    @NotBlank(message = "数据类型不能为空")
    private String dataType;
    /** Java类型*/
    @NotBlank(message = "Java类型不能为空")
    private String javaType;
    /** 注释*/
    @NotBlank(message = "注释不能为空")
    private String columnComment;

}
