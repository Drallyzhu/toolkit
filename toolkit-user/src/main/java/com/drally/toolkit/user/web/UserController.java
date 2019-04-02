package com.drally.toolkit.user.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drally.toolkit.common.domain.Page;
import com.drally.toolkit.common.util.ResponseBo;
import com.drally.toolkit.domain.user.domain.User;
import com.drally.toolkit.domain.user.model.UserBO;
import com.drally.toolkit.domain.user.model.UserListRequest;
import com.drally.toolkit.domain.user.model.UserResponse;
import com.drally.toolkit.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author gm
 * @Date 2019/3/11 15:27
 **/
@Slf4j
@RestController
@RequestMapping(value = "/user")
@RefreshScope
@Api(value="用户管理",tags={"用户操作接口"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    public ResponseBo<?> list(@RequestBody UserListRequest userListRequest){
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userListRequest, userBO);
        IPage<User> iPage = userService.selectPage(userBO);
        Page<UserResponse> page = new Page<>();
        List<UserResponse> userResponses = new ArrayList<>();
        page.setContent(userResponses);
        page.setNumber(iPage.getCurrent());
        page.setSize(iPage.getSize());
        page.setTotalPages(iPage.getPages());
        page.setTotalElements(iPage.getTotal());
        for (User user : iPage.getRecords()){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);
            userResponses.add(userResponse);
        }
        return ResponseBo.ok(page).setMessage("查询成功");
    }

    @GetMapping("/get")
    @ApiOperation(value = "获取用户")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "query")
    public ResponseBo<?> getModel(@RequestParam("id") String id){
        User user = userService.getModel(id);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return ResponseBo.ok(userResponse).setMessage("查询成功");
    }



}
