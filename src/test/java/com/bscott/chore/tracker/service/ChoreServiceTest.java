package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.ChoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ChoreServiceTest {

    @Mock
    private ChoreRepository choreRepository;
    @Mock
    private UserService userService;

    private ChoreService choreService;
    private List<Chore> chores = new ArrayList<>();
    private Chore choreOne = new Chore();
    private Chore choreTwo = new Chore();
    private User user;

    @Before
    public void setup(){

        choreService = new ChoreService(choreRepository, userService);

        user = new User("Emery", "Scott", LocalDate.parse("2005-04-09"),
                "escott", "emery@nowhere.com", "password");

        choreOne.setDescription("Clean your room");
        choreOne.setAssignees(new ArrayList<>());
        choreOne.getAssignees().add(user);

        choreTwo.setDescription("Do the Dishes");
        choreTwo.setAssignees(new ArrayList<>());
        choreTwo.getAssignees().add(user);    }

    @Test
    public void testGetChores_NullAssignee(){

        when(userService.findUserById(anyInt())).thenReturn(null);

        when(choreRepository.findAll()).thenReturn(chores);
        chores.add(choreOne);
        chores.add(choreTwo);

        List<Chore> chores = choreService.getChores(null);

        assertEquals(2, chores.size());
    }

    @Test
    public void testGetChores_ByAssignee(){

        when(userService.findUserById(anyInt())).thenReturn(user);

        when(choreRepository.findChoresByAssigneesEquals(user)).thenReturn(chores);
        chores.add(choreOne);

        List<Chore> chores = choreService.getChores(1);

        assertEquals(1, chores.size());
    }
}
