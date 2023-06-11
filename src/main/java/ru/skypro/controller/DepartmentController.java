package ru.skypro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.employee.model.Employee;
import ru.skypro.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("max-salary")
    public Employee findEmployeeWithMaxSalary(@RequestParam int departmentId){
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }
    @GetMapping("min-salary")
    public Employee findEmployeeWithMinSalary(@RequestParam int departmentId){
        return departmentService.findEmployeeWithMinSalary(departmentId);
    }
    @GetMapping("all")
    public Map<Integer, List<Employee>> findEmployeeByDepartment(){
        return departmentService.findEmployeeByDepartment();
    }
    @GetMapping(value = "all", params = {"departmentId"})
    public Collection<Employee> findEmployeeByDepartment(@RequestParam int departmentId){
        return departmentService.findEmployeeByDepartment(departmentId);
    }
}
