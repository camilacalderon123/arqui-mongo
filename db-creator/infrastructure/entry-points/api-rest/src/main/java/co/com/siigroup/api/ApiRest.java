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


    @PostMapping(path = "/createDocument")
    public Mono<String> commandName(@RequestParam("dbName") String dbName) throws Exception {
      return useCase.execute(dbName);
    }

    @PostMapping(path = "/executeCommand")
    public Mono<String> execute(@RequestParam("command") String command) throws Exception {
        return useCase.executeCommand(command);
    }
}
