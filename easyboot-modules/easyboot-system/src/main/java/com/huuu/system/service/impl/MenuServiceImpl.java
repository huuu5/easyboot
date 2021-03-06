package com.huuu.system.service.impl;

import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.system.condition.MenuCondition;
import com.huuu.system.entity.Menu;
import com.huuu.system.mapper.MenuMapper;
import com.huuu.system.mapper.RoleMenuMapper;
import com.huuu.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author huuu
 */
@Service
public class MenuServiceImpl extends AbstractServiceImpl<Menu, MenuMapper> implements MenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public boolean save(Menu entity) {
        if (Objects.equals(entity.getPid(), 0L)) {
            entity.setPids("0");
        } else {
            Menu parent = mapper.selectById(entity.getPid());
            entity.setPids(parent.getPids() + "," + parent.getId());
        }
        return returnBool(mapper.insert(entity));
    }

    @Override
    public boolean update(Menu entity) {
        Menu dbItem = mapper.selectById(entity.getId());
        if (!Objects.equals(entity.getPid(), 0L) && !Objects.equals(dbItem.getPid(), entity.getPid())) {
            Menu parent = mapper.selectById(entity.getPid());
            entity.setPids(parent.getPids() + "," + parent.getId());
        }
        return returnBool(mapper.updateById(entity));
    }

    @Override
    public boolean removeById(Long id) {
        roleMenuMapper.deleteByMenuId(id);
        return super.removeById(id);
    }

    @Override
    public Menu getByRouteName(String routeName) {
        return mapper.selectByRouteName(routeName);
    }

    @Override
    public List<Menu> listByPid(Long pid) {
        return mapper.selectByPid(pid);
    }

    @Override
    public List<Menu> listTreeByPid(Long pid) {
        return mapper.selectTreeByPid(pid);
    }

    @Override
    public List<Menu> listTreeByCondition(MenuCondition condition) {
        return mapper.selectTreeByCondition(condition);
    }

    @Override
    public List<Menu> listByUserId(Long userId) {
        return mapper.selectByUserId(userId);
    }
}
