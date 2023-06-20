package co.com.siigroup.usecase.employee;

import co.com.siigroup.model.employee.Employee;
import co.com.siigroup.model.employee.gateways.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


public class EmployeeUseCaseTest {

    @Mock
    EmployeeRepository employeeRepository;

    EmployeeUseCase employeeUseCase;

    Employee employee;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        employeeUseCase= new EmployeeUseCase(employeeRepository);
        employee= Employee.builder().employeeName("juan")
                .employeeAge(21)
                .employeeSalary(2323)
                .id("2").build();

    }

    @Test
    public void testAnualSalary(){
        float x=employeeUseCase.getSalaryPerYear(employee.getEmployeeSalary());
        float result=employee.getEmployeeSalary()*12;
        Assertions.assertEquals(result, x);
    }

    @Test
    public void testRestConsumer(){
        Mockito.when(employeeRepository.findAll()).thenReturn(Flux.just(employee));
        Flux<Employee> employees= employeeUseCase.getAll();
        StepVerifier.create(employees).expectSubscription().thenConsumeWhile(x -> true).verifyComplete();
    }
    
}
