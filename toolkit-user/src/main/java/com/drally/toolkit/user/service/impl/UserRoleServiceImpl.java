package com.drally.toolkit.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drally.toolkit.domain.user.domain.UserRole;
import com.drally.toolkit.domain.user.mapper.UserRoleMapper;
import com.drally.toolkit.user.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
