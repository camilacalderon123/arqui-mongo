package co.com.siigroup.api;
import co.com.siigroup.usecase.commandsexecutor.CommandsExecutorUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ApiRest {
    private final CommandsExecutorUseCase useCase;


    @GetMapping(path = "/commands/{dbName}")
    public Mono<String> commandName(@PathVariable String dbName) throws Exception {
      return useCase.execute(dbName);
    }
}
