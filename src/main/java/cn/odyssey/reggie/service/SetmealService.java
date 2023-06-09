package cn.odyssey.reggie.service;

import cn.odyssey.reggie.entity.Setmeal;
import cn.odyssey.reggie.entity.SetmealDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void removeWithDish(List<Long> ids);
}