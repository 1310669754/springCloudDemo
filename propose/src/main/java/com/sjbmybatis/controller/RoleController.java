package com.sjbmybatis.controller;

import com.sjbmybatis.common.ResponseResult;
import com.sjbmybatis.pojo.Role;
import com.sjbmybatis.pojo.bo.RoleBo;
import com.sjbmybatis.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述: RoleController
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:17 11:13:44
 */
@RestController
@RequestMapping("/mybatis/role")
@Api("mybatis-角色")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @return user list
     */
    @ApiOperation("Query Role List")
    @GetMapping("list")
    public ResponseResult<List<Role>> list(RoleBo roleQueryBean) {
        return ResponseResult.success(roleService.findList(roleQueryBean));
    }
}

