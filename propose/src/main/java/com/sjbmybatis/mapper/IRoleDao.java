package com.sjbmybatis.mapper;

import com.sjbmybatis.pojo.Role;
import com.sjbmybatis.pojo.bo.RoleBo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>描述: OaMaterialDictEnclosure</p>
 * <p>公司: 瑞华康源科技有限公司</p>
 * <p>版权: rivamed2021</p>
 *
 * @author sjb
 * @version V1.2.11
 * @date 2022:06:17 11:00:15
 */
@Mapper
@Component
public interface IRoleDao {
    List<Role> findList(RoleBo roleQueryBean);
}
