package br.com.rts.estudos.graphql.paginationexample.speaker;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends ListCrudRepository<Speaker, Integer> {
}
