package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.ChoreRepository;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ChoreServiceTest {

    @InjectMocks
    private ChoreService choreService = new ChoreService();

    @Mock
    private ChoreRepository choreRepository;

    private List<Chore> chores = new ArrayList<>();
    private Chore choreOne = new Chore();
    private Chore choreTwo = new Chore();
    private User user;

    @Before
    public void setup(){

        user = new User("Emery", "Scott", new LocalDate("2005-04-09"),
                "escott", "emery@nowhere.com", "password");

        choreOne.setDescription("Clean your room");
        choreOne.setAssignee(user);

        choreOne.setDescription("Do the Dishes");
        choreOne.setAssignee(user);
    }

    @Test
    public void testGetChores_NullAssignee(){

        when(choreRepository.findAll()).thenReturn(chores);
        chores.add(choreOne);
        chores.add(choreTwo);

        List<Chore> chores = choreService.getChores(null);

        assertEquals(2, chores.size());
    }

    @Test
    public void testGetChores_ByAssignee(){

        when(choreRepository.findChoresByAssigneeId("Emery")).thenReturn(chores);
        chores.add(choreOne);

        List<Chore> chores = choreService.getChores("Emery");

        assertEquals(1, chores.size());
    }
}
