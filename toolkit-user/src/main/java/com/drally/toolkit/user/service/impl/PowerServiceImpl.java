package com.drally.toolkit.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drally.toolkit.domain.user.domain.Power;
import com.drally.toolkit.domain.user.mapper.PowerMapper;
import com.drally.toolkit.user.service.PowerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-28
 */
@Service
public class PowerServiceImpl extends ServiceImpl<PowerMapper, Power> implements PowerService {

}
