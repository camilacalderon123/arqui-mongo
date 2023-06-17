package co.com.siigroup.usecase.employee;

import co.com.siigroup.model.employee.Employee;
import co.com.siigroup.model.employee.gateways.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public Mono<Employee> getById(Integer id){
        return employeeRepository.getById(id)
                .map(x -> {
                    x.setYearSalary(getSalaryPerYear(x.getEmployeeSalary()));
                    return x;
                });
    }

    public Flux<Employee> getAll(){
        return employeeRepository.findAll()
                .map(x -> {
                    x.setYearSalary(getSalaryPerYear(x.getEmployeeSalary()));
                    return x;
                });
    }

    public float getSalaryPerYear(float salary){
        return salary*12;
    }
}
