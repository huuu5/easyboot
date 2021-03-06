package com.huuu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huuu.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户部门关联
 * @author huuu
 */
@Getter
@Setter
@TableName("sys_user_dept")
public class UserDept extends BaseEntity {
    private static final long serialVersionUID = -165163747105455582L;

    /** 用户id*/
    private Long userId;
    /** 部门id*/
    private Long deptId;
}
