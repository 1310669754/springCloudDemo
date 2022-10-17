package com.sjbmybatis.pojo.bo;


import com.sjbmybatis.pojo.Role;

import java.util.List;

/**
 * 描述: RoleBo
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:16 15:53:19
 */
@lombok.Data
@lombok.EqualsAndHashCode(callSuper=false)
public class RoleBo extends Role {

    private Role role;

    private List<Role> roleList;
}
