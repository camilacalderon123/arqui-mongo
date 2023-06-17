package co.com.siigroup.api;
import co.com.siigroup.model.employee.Employee;
import co.com.siigroup.usecase.employee.EmployeeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET})
public class ApiRest {
    private final EmployeeUseCase useCase;


    @GetMapping(path = "/employees")
        public ResponseEntity<Flux<Employee>> getAll() {

        try {

            Flux<Employee> employees=useCase.getAll();

            return new ResponseEntity<>(employees, HttpStatus.OK);

        }catch(RuntimeException e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/employee/{id}")
    public Mono<Employee> getById(@PathVariable Integer id){
        try {
            Mono<Employee> employee= useCase.getById(id);
            return employee;
        }catch (RuntimeException e){
            return null;
        }

    }
}
