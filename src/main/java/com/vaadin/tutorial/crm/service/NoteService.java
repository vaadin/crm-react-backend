package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Note;
import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.NoteRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NoteService {
    private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());
    private NoteRepository noteRepository;
    private DealRepository dealRepository;

    public NoteService(NoteRepository noteRepository, DealRepository dealRepository) {
        this.noteRepository = noteRepository;
        this.dealRepository = dealRepository;
    }

    @PostConstruct
    public void populateTestData() {
        if (noteRepository.count() == 0) {
            Random r = new Random(0);
            List<Deal> deals = dealRepository.findAll();

            noteRepository.saveAll(
                IntStream.range(1, 81)
                    .mapToObj(idx -> {
                        Note note = new Note();
                        note.setText("note " + idx);
                        Timestamp start = Timestamp.valueOf("2021-1-07 00:00:00.000");
                        Timestamp end = new Timestamp(System.currentTimeMillis());
                        note.setCreatedAt(new Timestamp(r.nextLong() % (end.getTime() - start.getTime()) + start.getTime()));
                        note.setDeal(deals.get(r.nextInt(deals.size())));
                        return note;
                    })
                    .collect(Collectors.toList()));
        }
    }
}
