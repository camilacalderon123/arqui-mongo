package co.com.siigroup.model.commandsexecutor.gateways;

import reactor.core.publisher.Mono;

public interface CommandsExecutorRepository {

    public Mono<String> run(String command ) throws Exception;
}
