package com.sjbmybatis.service;

import com.sjbmybatis.pojo.Role;
import com.sjbmybatis.pojo.bo.RoleBo;

import java.util.List;

/**
 * <p>描述: OaMaterialDictEnclosure</p>
 * <p>公司: 瑞华康源科技有限公司</p>
 * <p>版权: rivamed2021</p>
 *
 * @author sjb
 * @version V1.2.11
 * @date 2022:06:17 11:06:05
 */


public interface IRoleService {

    List<Role> findList(RoleBo roleQueryBean);

}
