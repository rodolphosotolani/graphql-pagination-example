package br.com.rts.estudos.graphql.paginationexample.speaker;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerRepository repository;

    @QueryMapping
    List<Speaker> speakers(){
        return repository.findAll();
    }

    @QueryMapping
    Optional<Speaker> speaker(@Argument Integer id){
        return repository.findById(id);
    }
}
