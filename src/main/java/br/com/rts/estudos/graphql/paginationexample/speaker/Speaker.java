package br.com.rts.estudos.graphql.paginationexample.speaker;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Speaker {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String title;

    private String company;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    private String country;

    private String email;

    private String twitter;

}
