package br.com.rts.estudos.graphql.paginationexample;

import br.com.rts.estudos.graphql.paginationexample.event.Event;
import br.com.rts.estudos.graphql.paginationexample.event.EventRepository;
import br.com.rts.estudos.graphql.paginationexample.session.Level;
import br.com.rts.estudos.graphql.paginationexample.session.Session;
import br.com.rts.estudos.graphql.paginationexample.session.SessionRepository;
import br.com.rts.estudos.graphql.paginationexample.speaker.Gender;
import br.com.rts.estudos.graphql.paginationexample.speaker.Speaker;
import br.com.rts.estudos.graphql.paginationexample.speaker.SpeakerRepository;
import br.com.rts.estudos.graphql.paginationexample.tag.Tag;
import br.com.rts.estudos.graphql.paginationexample.tag.TagRepository;
import graphql.com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class DataLoaderCommandLineRunner implements CommandLineRunner {

    public static final int COUNT_TAGS = 20;
    public static final int COUNT_SESSIONS = 100;
    public static final int COUNT_SPEAKERS = 1000;
    public static final int COUNT_EVENTS = 10;

    private final EventRepository eventRepository;

    private final SpeakerRepository speakerRepository;

    private final SessionRepository sessionRepository;

    private final TagRepository tagRepository;

    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        this.createTags();
        this.createSpeakers();
        this.createEvents()
                .forEach(this::createSessions);
    }

    private void createSessions(Event event) {

        log.info("Creating the Sessions List in dataBase");

        for (int count = 0; count < COUNT_SESSIONS; count++) {
            sessionRepository.save(Session
                    .builder()
                    .title(faker.book().title())
                    .description(faker.lorem().paragraph())
                    .level(Level.values()[faker.number().numberBetween(0, Level.values().length)])
//                    .tags(new HashSet<>(tagRepository.findAll()))
                    .event(event)
                    .build());
        }
    }

    private void createSpeakers() {

        log.info("Creating the Speakers List in dataBase");

        for (int count = 0; count < COUNT_SPEAKERS; count++) {
            speakerRepository.save(Speaker
                    .builder()
                    .title(faker.name().title())
                    .email(faker.internet().emailAddress())
                    .company(faker.company().name())
                    .country(faker.country().name())
                    .gender(Gender.MALE)
                    .twitter(faker.twitter().userName())
                    .name(faker.name().fullName())
                    .build());
        }
    }

    private List<Event> createEvents() {

        log.info("Creating the Events List in dataBase");

        var events = new ArrayList<Event>();
        for (int count = 0; count < COUNT_EVENTS; count++) {

            var startDate = LocalDate.ofInstant(
                    faker.timeAndDate().future(faker.number().randomNumber(3, true), TimeUnit.DAYS), ZoneId.systemDefault());
            var endDate = startDate.plusDays(faker.number().numberBetween(1, 10));
            var cfpStartDate = LocalDate.ofInstant(
                    faker.timeAndDate().past(faker.number().numberBetween(1, 180), TimeUnit.DAYS), ZoneId.systemDefault());
            var cfpEndDate = cfpStartDate.minusDays(faker.number().numberBetween(1, 180));

            events.add(Event
                    .builder()
                    .name(faker.marvelSnap().event())
                    .description(faker.marvelSnap().toString())
                    .startDate(startDate)
                    .endDate(endDate)
                    .cfpStartDate(cfpStartDate)
                    .cfpEndDate(cfpEndDate)
                    .location(faker.address().cityName())
                    .website(faker.internet().webdomain())
                    .build());
        }

        log.info("Saving the {} events...", events.size());
        return eventRepository.saveAll(events);
    }

    private void createTags() {

        log.info("Creating the Tags List in dataBase");
        for (int count = 0; count < COUNT_TAGS; count++) {
            tagRepository
                    .save(Tag
                            .builder()
                            .name(faker.programmingLanguage().name())
                            .build());
        }
    }

}
