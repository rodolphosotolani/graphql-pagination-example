package br.com.rts.estudos.graphql.paginationexample.tag;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends ListCrudRepository<Tag, Integer> {
}
