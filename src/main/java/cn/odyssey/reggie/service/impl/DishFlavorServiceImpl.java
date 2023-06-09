package cn.odyssey.reggie.service.impl;

import cn.odyssey.reggie.entity.DishFlavor;
import cn.odyssey.reggie.mapper.DishFlavorMapper;
import cn.odyssey.reggie.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
