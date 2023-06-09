package cn.odyssey.reggie.service.impl;

import cn.odyssey.reggie.common.CustomException;
import cn.odyssey.reggie.entity.Category;
import cn.odyssey.reggie.entity.Dish;
import cn.odyssey.reggie.entity.Setmeal;
import cn.odyssey.reggie.mapper.CategoryMapper;
import cn.odyssey.reggie.service.CategoryService;
import cn.odyssey.reggie.service.DishService;
import cn.odyssey.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int countDish = dishService.count(dishLambdaQueryWrapper);
        log.info("dish count = {}", countDish);
        if (countDish > 0) {
            // 关联了菜品
            throw new CustomException("当前分类下关联了菜品，因此不能删除！");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int countSet = setmealService.count(setmealLambdaQueryWrapper);
        log.info("set meal count = {}", countSet);
        if (countSet > 0) {
            throw new CustomException("当前分类下关联了套餐，因此不能删除！");
        }
        super.removeById(id);
    }
}
