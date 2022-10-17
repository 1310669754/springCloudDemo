package com.sjbmybatis.controller;

import com.sjbmybatis.common.ResponseResult;
import com.sjbmybatis.pojo.User;
import com.sjbmybatis.pojo.bo.UserBo;
import com.sjbmybatis.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 描述: UserController
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:17 11:10:59
 */
@RestController
@RequestMapping("/mybatis/user")
@Api("mybatis-用户")
public class UserController {

    @Resource
    private IUserService userService;

//    public UserController(IUserService userService) {
//        this.userService = userService;
//    }

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Add/Edit User")
    @PostMapping("add")
    public ResponseResult<User> add(User user) {
        if (user.getId()==null) {
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userService.save(user);
        } else {
            user.setUpdateTime(LocalDateTime.now());
            userService.update(user);
        }
        return ResponseResult.success(userService.findById(user.getId()));
    }


    /**
     * @return user list
     */
    @ApiOperation("Query User One")
    @GetMapping("edit/{userId}")
    public ResponseResult<User> edit(@PathVariable("userId") String userId) {
        return ResponseResult.success(userService.findById(userId));
    }

    /**
     * @return user list
     */
    @ApiOperation("Query User List")
    @GetMapping("list")
    public ResponseResult<List<User>> list(UserBo userQueryBean) {
        return ResponseResult.success(userService.findList(userQueryBean));
    }

    /**
     * @return user list
     */
    @ApiOperation("通过Feign调用外部接口")
    @GetMapping("findByIdByFeign")
    public ResponseResult<String> findByIdByFeign(String userId) {
        String byIdByFeign = userService.findByIdByFeign(userId);
        return ResponseResult.success(byIdByFeign);
    }
}
