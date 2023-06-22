package co.com.siigroup.mongo.commands;

import co.com.siigroup.model.commandsexecutor.gateways.CommandsExecutorRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.reactivestreams.client.MongoClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandExecutor implements CommandsExecutorRepository {

    @Autowired
    private  ReactiveMongoTemplate template;

    private final MongoClient client;


    @Override
    public Mono<String> run(String command ) throws Exception {
        selectDatabase(command);
        String createCommand = String.format("{'create': '%s'}", command);
        Document commandDocument = Document.parse(createCommand);
        return template.executeCommand(commandDocument)
                .flatMap(result -> Mono.just(result.toJson()));
    }

    private void selectDatabase(String dbName){
        template = new ReactiveMongoTemplate(client, dbName);
    }

    @Override
    public Mono<String> runCommand(String command) throws Exception {
        String[] st = command.split(":");
        selectDatabase(st[0]);
        String convertCommand = String.format("{'%s': '%s'}", st[1], st[2]);
        return template.executeCommand(convertCommand)
                .flatMap(result -> Mono.just(result.toJson()));
    }
}
