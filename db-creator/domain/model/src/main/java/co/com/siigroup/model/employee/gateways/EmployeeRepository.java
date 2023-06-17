package co.com.siigroup.model.employee.gateways;

import co.com.siigroup.model.employee.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    public Mono<Employee> getById(Integer id);
    public Flux<Employee> findAll();
}
