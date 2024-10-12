package br.com.rts.estudos.graphql.paginationexample.session;

import br.com.rts.estudos.graphql.paginationexample.event.Event;
import br.com.rts.estudos.graphql.paginationexample.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany
    @ToString.Exclude
//    @JoinTable(
//            name = "session_tags",
//            joinColumns = @JoinColumn(name = "session_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToOne
    @ToString.Exclude
    private Event event;

}
