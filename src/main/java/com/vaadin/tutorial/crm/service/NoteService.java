package com.vaadin.tutorial.crm.service;

import com.vaadin.tutorial.crm.entity.Note;
import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.entity.User;
import com.vaadin.tutorial.crm.model.NoteDTO;
import com.vaadin.tutorial.crm.repository.DealRepository;
import com.vaadin.tutorial.crm.repository.NoteRepository;
import com.vaadin.tutorial.crm.repository.UserRepository;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import io.jsonwebtoken.Jwts;

@Service
public class NoteService {
    private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());
    private NoteRepository noteRepository;
    private DealRepository dealRepository;
    private UserRepository userRepository;

    public NoteService(
        NoteRepository noteRepository,
        DealRepository dealRepository,
        UserRepository userRepository)
    {
        this.noteRepository = noteRepository;
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    public List<NoteDTO> findByDeal(String deal) {
        List<NoteDTO> result = noteRepository.search(deal);
        return result;
    }

    public void save(Note n, String user) throws IOException {
        Deal deal = n.getDeal();
        String text = n.getText();

        if (text == null || user == null || deal == null) {
            throw new IOException("No data");
        }

        String username = Jwts.parser().setSigningKey("SecretKeyToGenJWTs".getBytes())
            .parseClaimsJws(user.replaceAll("Bearer ",""))
            .getBody().getSubject();

        Optional<Deal> optDeal = dealRepository.findById(deal.getId());
        if (!optDeal.isPresent()) {
            throw new IOException("No deal");
        }

        Note note = new Note();
        note.setText(text);
        note.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        note.setUser(userRepository.findByName(username));
        note.setDeal(optDeal.get());
        noteRepository.save(note);
    }

    @PostConstruct
    public void populateTestData() {
        if (noteRepository.count() == 0) {
            Random r = new Random(0);
            List<Deal> deals = dealRepository.findAll();
            List<User> users = userRepository.findAll();

            noteRepository.saveAll(
                IntStream.range(1, 81)
                    .mapToObj(idx -> {
                        Note note = new Note();
                        note.setText("note " + idx);
                        Timestamp start = Timestamp.valueOf("2021-1-07 00:00:00.000");
                        Timestamp end = new Timestamp(System.currentTimeMillis());
                        note.setCreatedAt(new Timestamp(r.nextLong() % (end.getTime() - start.getTime()) + start.getTime()));
                        note.setDeal(deals.get(r.nextInt(deals.size())));
                        note.setUser(users.get(r.nextInt(users.size())));
                        return note;
                    })
                    .collect(Collectors.toList()));
        }
    }
}
