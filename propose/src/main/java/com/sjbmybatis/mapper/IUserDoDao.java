package com.sjbmybatis.mapper;

import com.sjbmybatis.pojo.User;
import com.sjbmybatis.pojo.bo.UserBo;
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
 * @date 2022:06:17 10:54:21
 */
@Mapper
@Component
public interface IUserDoDao {
    List<User> findList(UserBo userQueryBean);

    User findById(String id);

    int deleteById(String id);

    int deleteByIds(String[] ids);

    int update(User user);

    int save(User user);

    int updatePassword(User user);
}
