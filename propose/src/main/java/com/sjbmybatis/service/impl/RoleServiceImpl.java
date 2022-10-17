package com.sjbmybatis.service.impl;

import com.sjbmybatis.mapper.IRoleDao;
import com.sjbmybatis.pojo.Role;
import com.sjbmybatis.pojo.bo.RoleBo;
import com.sjbmybatis.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: RoleServiceImpl
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:17 11:06:45
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    /**
     * roleDao.
     */
    @Resource
    private  IRoleDao roleDao;


    @Override
    public List<Role> findList(RoleBo roleQueryBean) {
        return roleDao.findList(roleQueryBean);
    }
}

