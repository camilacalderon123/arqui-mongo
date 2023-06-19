package co.com.siigroup.usecase.commandsexecutor;

import co.com.siigroup.model.commandsexecutor.gateways.CommandsExecutorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CommandsExecutorUseCase {

    private final CommandsExecutorRepository repository;

    public Mono<String> execute(String command) throws Exception {
       return repository.run(command);
    }
}
