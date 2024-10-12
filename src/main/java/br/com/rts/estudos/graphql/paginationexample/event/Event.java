package br.com.rts.estudos.graphql.paginationexample.event;

import br.com.rts.estudos.graphql.paginationexample.session.Session;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate cfpStartDate;

    private LocalDate cfpEndDate;

    private String location;

    private String website;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Session> sessions = new HashSet<>();
}
