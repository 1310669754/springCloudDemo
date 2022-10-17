package com.sjbmybatis.service.impl;

import com.sjbmybatis.mapper.IUserDoDao;
import com.sjbmybatis.pojo.User;
import com.sjbmybatis.pojo.bo.UserBo;
import com.sjbmybatis.service.IUserService;
import com.sjbmybatis.service.feign.IomsFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: UserServiceImpl
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:17 11:04:02
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    /**
     * iUserDoDao.
     */
    @Resource
    private  IUserDoDao iUserDoDao;
    @Resource
    private IomsFeign iomsFeign;


    @Override
    public List<User> findList(UserBo userQueryBean) {
        return iUserDoDao.findList(userQueryBean);
    }

    @Override
    public User findById(String id) {
        return iUserDoDao.findById(id);
    }

    @Override
    public int deleteById(String id) {
        return iUserDoDao.deleteById(id);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return iUserDoDao.deleteByIds(ids);
    }

    @Override
    public int update(User user) {
        return iUserDoDao.update(user);
    }

    @Override
    public int save(User user) {
        return iUserDoDao.save(user);
    }

    @Override
    public int updatePassword(User user) {
        return iUserDoDao.updatePassword(user);
    }

    public String findByIdByFeign(String userId){
       return iomsFeign.findByIdByFeign(userId);
    }
}
