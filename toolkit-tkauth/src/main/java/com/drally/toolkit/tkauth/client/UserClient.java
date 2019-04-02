package com.drally.toolkit.tkauth.client;

import com.drally.toolkit.domain.auth.model.AuthUser;
import com.drally.toolkit.common.util.ResponseBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserClient
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 14:38
 **/
@FeignClient("toolkit-user")
@RequestMapping(value = "/rpc/user")
public interface UserClient {

    /**
     * 获取用户
     * @param username
     * @return
     */
    @GetMapping(value = "/findByCode")
    public ResponseBo<AuthUser> findByCode(@RequestParam("username") String username);

    /**
     * 保存用户
     * @param authUser
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseBo<Boolean> save(@RequestBody AuthUser authUser);

}
