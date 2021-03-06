package com.huuu.web.system.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.request.PageRequest;
import com.huuu.system.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色列表请求参数
 * @author chenzhenhu
 */
@Getter
@Setter
public class RoleListRequest extends PageRequest<Role> {
    private static final long serialVersionUID = 4259086381362181253L;

    /** 角色名称*/
    private String name;
    /** 状态： 0-正常 1-禁用*/
    private Integer status;

    @Override
    public LambdaQueryWrapper<Role> queryWrapper() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Role::getName, name);
        }
        if (null != status) {
            queryWrapper.eq(Role::getStatus, status);
        }
        return queryWrapper;
    }
}
