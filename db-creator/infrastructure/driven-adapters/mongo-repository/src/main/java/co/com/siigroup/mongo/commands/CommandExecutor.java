package co.com.siigroup.mongo.commands;

import co.com.siigroup.model.commandsexecutor.gateways.CommandsExecutorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CommandExecutor implements CommandsExecutorRepository {


    private final ReactiveMongoTemplate template;


    @Override
    public Mono<String> run(String command ) throws Exception {
        String createCommand = String.format("{'create': '%s'}", command);
        Document commandDocument = Document.parse(createCommand);

        return template.executeCommand(commandDocument)
                .flatMap(result -> Mono.just(result.toJson()));
    }
}
