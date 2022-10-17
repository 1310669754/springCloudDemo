package com.sjbmybatis.pojo.bo;


import com.sjbmybatis.pojo.User;

import java.util.List;

/**
 * 描述: UserBo
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:16 14:32:03
 */
@lombok.Data
@lombok.EqualsAndHashCode(callSuper=false)
public class UserBo extends User {
    private List<User> userList;
}
