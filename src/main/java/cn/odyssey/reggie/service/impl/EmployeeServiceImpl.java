package cn.odyssey.reggie.service.impl;

import cn.odyssey.reggie.entity.Employee;
import cn.odyssey.reggie.mapper.EmployeeMapper;
import cn.odyssey.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
