package com.sjbmybatis.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>描述: OaMaterialDictEnclosure</p>
 * <p>公司: 瑞华康源科技有限公司</p>
 * <p>版权: rivamed2021</p>
 *
 * @author sjb
 * @version V1.2.11
 * @date 2022:06:23 16:25:20
 */
@FeignClient(url = "${iomsFeign.url}",name = "userFeign")
public interface IomsFeign {

    @RequestMapping(value = "/user/findById",method = RequestMethod.GET)
    public String findByIdByFeign(@RequestParam(value = "userId") String userId);
}
