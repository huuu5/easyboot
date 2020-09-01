package com.hu4java.system.response;

import com.hu4java.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单表格树
 * @author hu4java
 */
@Getter
@Setter
public class MenuTreeTableResponse extends BaseResponse {
    private static final long serialVersionUID = -4742672786101353847L;

    private Long id;
    /** 标题*/
    private String title;
    /** 权限码*/
    private String code;
    /** 上级id*/
    private Long pid;
    /** 路径*/
    private String path;
    /** 图标*/
    private String icon;
    /** 类型：1-目录 2-菜单 3-按钮*/
    private Integer type;
    /** 状态： 0-正常 1-禁用*/
    private Integer status;
    /** 排序*/
    private Integer orderNum;
    /** 子菜单*/
    private List<MenuTreeTableResponse> children;
}
