package br.com.rts.estudos.graphql.paginationexample.session;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ListCrudRepository<Session, Integer> {
}
