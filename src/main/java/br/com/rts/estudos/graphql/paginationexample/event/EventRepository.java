package br.com.rts.estudos.graphql.paginationexample.event;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ListCrudRepository<Event, Integer> {
}
