package co.com.siigroup.consumer;

import co.com.siigroup.consumer.utils.JSONObject;
import co.com.siigroup.model.employee.Employee;
import co.com.siigroup.model.employee.gateways.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestConsumer implements EmployeeRepository {

    private final WebClient client;



    @Override
    public Mono<Employee> getById(Integer id) {
         Mono<Employee> employee= consumerById(id)
                 .map(response -> {
                    return JSONObject.stringToEmployee(response.getData().toString());
                 });
        return employee;
    }

    @Override
    public Flux<Employee> findAll() {
      return consumer()
              .map(x ->{
                 return JSONObject.covertToListEmployee(x.getData().toString());
              }).flatMapMany(Flux::fromIterable);
    }

    public Mono<ObjectResponse> consumer(){
        return this.client.get()
                .uri("/employees")
                .retrieve().onStatus(HttpStatus::isError, response -> {
                    return Mono.error(Exception::new);
                }).bodyToMono(ObjectResponse.class)
                .timeout(Duration.ofSeconds(30))
                .onErrorResume(throwable -> Mono.just(this.onErrorService(throwable)))
                .map(tr -> {System.out.println(tr); return tr;});
    }

    public Mono<ObjectResponse> consumerById(Integer id){
        return this.client.get()
                .uri("/employee/" + id)
                .retrieve().onStatus(HttpStatus::isError, response -> {
                    return Mono.error(Exception::new);
                }).bodyToMono(ObjectResponse.class)
                .timeout(Duration.ofSeconds(30))
                .onErrorResume(throwable -> Mono.just(this.onErrorService(throwable)))
                .map(tr -> {System.out.println(tr); return tr;});
    }

    public ObjectResponse onErrorService(Throwable throwable){
        return ObjectResponse.builder().data("ERROR")
                .message("Ocurrio un error al consumir el servicio")
                .data("error al comsumir")
                .build();
    }
}