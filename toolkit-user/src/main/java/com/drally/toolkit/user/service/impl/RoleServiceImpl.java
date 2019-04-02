package com.drally.toolkit.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drally.toolkit.domain.user.domain.Role;
import com.drally.toolkit.domain.user.mapper.RoleMapper;
import com.drally.toolkit.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
