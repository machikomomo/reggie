package cn.odyssey.reggie.dto;

import cn.odyssey.reggie.entity.Dish;
import cn.odyssey.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
