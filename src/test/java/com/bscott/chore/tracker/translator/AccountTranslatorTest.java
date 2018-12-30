package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Account;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.AccountDto;
import com.bscott.chore.tracker.dto.UserDto;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AccountTranslatorTest {

    private AccountTranslator accountTranslator = new AccountTranslator();

    @Test
    public void testToDto(){

        User user = new User();
        user.setId("583a9be33004dfd16b956697");
        user.setFirstName("Brendon");
        user.setLastName("Scott");
        user.setBirthDate(new LocalDate("1975-12-19"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(0);
        user.getRoles().add("admin");
        user.getRoles().add("user");

        User child = new User();
        child.setId("583a9be33004dfd16b956698");
        child.setFirstName("Emery");
        child.setLastName("Scott");
        child.setBirthDate(new LocalDate("2005-04-09"));
        child.setMoneyEarned(BigDecimal.ZERO);
        child.setPointsEarned(0);
        child.getRoles().add("user");

        Account account = new Account();
        account.setFamilyName("Scott Family");
        account.setOwner(user);
        account.getFamilyMembers().add(child);

        AccountDto accountDto = accountTranslator.toDto(account);

        assertEquals(account.getFamilyName(), accountDto.getFamilyName());
        assertEquals(account.getId(), accountDto.getId());
        assertEquals(account.getOwner().getId(), accountDto.getOwner().getId());
        assertEquals(account.getOwner().getFirstName(), accountDto.getOwner().getFirstName());
        assertEquals(account.getOwner().getLastName(), accountDto.getOwner().getLastName());
        assertEquals(account.getOwner().getBirthDate().toString("MM/dd/YYYY"), accountDto.getOwner().getBirthDate());
        assertEquals(account.getOwner().getMoneyEarned(), accountDto.getOwner().getMoneyEarned());
        assertEquals(account.getOwner().getPointsEarned(), accountDto.getOwner().getPointsEarned());

        assertEquals(1, accountDto.getFamilyMembers().size());
        assertEquals(account.getFamilyMembers().get(0).getFirstName(), accountDto.getFamilyMembers().get(0).getFirstName());
        assertEquals(account.getFamilyMembers().get(0).getLastName(), accountDto.getFamilyMembers().get(0).getLastName());
        assertEquals(account.getFamilyMembers().get(0).getBirthDate().toString("MM/dd/YYYY"), accountDto.getFamilyMembers().get(0).getBirthDate());
        assertEquals(account.getFamilyMembers().get(0).getPointsEarned(), accountDto.getFamilyMembers().get(0).getPointsEarned());
        assertEquals(account.getFamilyMembers().get(0).getMoneyEarned(), accountDto.getFamilyMembers().get(0).getMoneyEarned());

        assertEquals(1, accountDto.getFamilyMembers().get(0).getRoles().size());
        assertTrue(accountDto.getFamilyMembers().get(0).getRoles().contains("user"));
    }

    @Test
    public void testToDto_Null(){

        AccountDto accountDto = accountTranslator.toDto(null);
        assertNull(accountDto);
    }

    @Test
    public void testToEntity(){

        UserDto userDto = new UserDto();
        userDto.setId("583a9be33004dfd16b956697");
        userDto.setFirstName("Brendon");
        userDto.setLastName("Scott");
        userDto.setBirthDate("12/19/1975");
        userDto.setMoneyEarned(BigDecimal.ZERO);
        userDto.setPointsEarned(0);
        userDto.getRoles().add("admin");
        userDto.getRoles().add("user");

        UserDto childDto = new UserDto();
        childDto.setId("583a9be33004dfd16b956698");
        childDto.setFirstName("Emery");
        childDto.setLastName("Scott");
        childDto.setBirthDate("04/09/2005");
        childDto.setMoneyEarned(BigDecimal.ZERO);
        childDto.setPointsEarned(0);
        childDto.getRoles().add("user");

        AccountDto accountDto = new AccountDto();
        accountDto.setFamilyName("Scott Family");
        accountDto.setOwner(userDto);
        accountDto.getFamilyMembers().add(childDto);

        Account account = accountTranslator.toEntity(accountDto);

        assertEquals(accountDto.getFamilyName(), account.getFamilyName());
        assertEquals(accountDto.getId(), account.getId());
        assertEquals(accountDto.getOwner().getId(), account.getOwner().getId());
        assertEquals(accountDto.getOwner().getFirstName(), account.getOwner().getFirstName());
        assertEquals(accountDto.getOwner().getLastName(), account.getOwner().getLastName());
        assertEquals(accountDto.getOwner().getBirthDate(), account.getOwner().getBirthDate().toString("MM/dd/YYYY"));
        assertEquals(accountDto.getOwner().getMoneyEarned(), account.getOwner().getMoneyEarned());
        assertEquals(accountDto.getOwner().getPointsEarned(), account.getOwner().getPointsEarned());

        assertEquals(1, account.getFamilyMembers().size());
        assertEquals(accountDto.getFamilyMembers().get(0).getFirstName(), account.getFamilyMembers().get(0).getFirstName());
        assertEquals(accountDto.getFamilyMembers().get(0).getLastName(), account.getFamilyMembers().get(0).getLastName());
        assertEquals(accountDto.getFamilyMembers().get(0).getBirthDate(), account.getFamilyMembers().get(0).getBirthDate().toString("MM/dd/YYYY"));
        assertEquals(accountDto.getFamilyMembers().get(0).getPointsEarned(), account.getFamilyMembers().get(0).getPointsEarned());
        assertEquals(accountDto.getFamilyMembers().get(0).getMoneyEarned(), account.getFamilyMembers().get(0).getMoneyEarned());

        assertEquals(1, account.getFamilyMembers().get(0).getRoles().size());
        assertTrue(account.getFamilyMembers().get(0).getRoles().contains("user"));
    }

    @Test
    public void testToEntity_Null(){

        Account account = accountTranslator.toEntity(null);
        assertNull(account);
    }

    @Test
    public void testToDtos(){

        // Account 1
        User user1 = new User();
        user1.setId("583a9be33004dfd16b956697");
        user1.setFirstName("Brendon");
        user1.setLastName("Scott");
        user1.setBirthDate(new LocalDate("1975-12-19"));
        user1.setMoneyEarned(BigDecimal.ZERO);
        user1.setPointsEarned(0);
        user1.getRoles().add("admin");
        user1.getRoles().add("user");

        User child1 = new User();
        child1.setId("583a9be33004dfd16b956698");
        child1.setFirstName("Emery");
        child1.setLastName("Scott");
        child1.setBirthDate(new LocalDate("2005-04-09"));
        child1.setMoneyEarned(BigDecimal.ZERO);
        child1.setPointsEarned(0);
        child1.getRoles().add("user");

        Account account1 = new Account();
        account1.setFamilyName("Scott Family");
        account1.setOwner(user1);
        account1.getFamilyMembers().add(child1);

        // Account 2
        User user2 = new User();
        user2.setId("583a9be33004dfd16b956695");
        user2.setFirstName("Clark");
        user2.setLastName("Griswold");
        user2.setBirthDate(new LocalDate("1960-11-11"));
        user2.setMoneyEarned(BigDecimal.ZERO);
        user2.setPointsEarned(0);
        user2.getRoles().add("admin");
        user2.getRoles().add("user");

        User child2 = new User();
        child2.setId("583a9be33004dfd16b956696");
        child2.setFirstName("Rusty");
        child2.setLastName("Griswold");
        child2.setBirthDate(new LocalDate("1995-03-01"));
        child2.setMoneyEarned(BigDecimal.ZERO);
        child2.setPointsEarned(0);
        child2.getRoles().add("user");

        Account account2 = new Account();
        account2.setFamilyName("Griswold Family");
        account2.setOwner(user2);
        account2.getFamilyMembers().add(child2);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        List<AccountDto> accountDtos = accountTranslator.toDtos(accounts);

        // Account 1
        assertEquals(accounts.get(0).getFamilyName(), accountDtos.get(0).getFamilyName());
        assertEquals(accounts.get(0).getId(), accountDtos.get(0).getId());
        assertEquals(accounts.get(0).getOwner().getId(), accountDtos.get(0).getOwner().getId());
        assertEquals(accounts.get(0).getOwner().getFirstName(), accountDtos.get(0).getOwner().getFirstName());
        assertEquals(accounts.get(0).getOwner().getLastName(), accountDtos.get(0).getOwner().getLastName());
        assertEquals(accounts.get(0).getOwner().getBirthDate().toString("MM/dd/YYYY"), accountDtos.get(0).getOwner().getBirthDate());
        assertEquals(accounts.get(0).getOwner().getMoneyEarned(), accountDtos.get(0).getOwner().getMoneyEarned());
        assertEquals(accounts.get(0).getOwner().getPointsEarned(), accountDtos.get(0).getOwner().getPointsEarned());

        assertEquals(1, accountDtos.get(0).getFamilyMembers().size());
        assertEquals(accounts.get(0).getFamilyMembers().get(0).getFirstName(), accountDtos.get(0).getFamilyMembers().get(0).getFirstName());
        assertEquals(accounts.get(0).getFamilyMembers().get(0).getLastName(), accountDtos.get(0).getFamilyMembers().get(0).getLastName());
        assertEquals(accounts.get(0).getFamilyMembers().get(0).getBirthDate().toString("MM/dd/YYYY"), accountDtos.get(0).getFamilyMembers().get(0).getBirthDate());
        assertEquals(accounts.get(0).getFamilyMembers().get(0).getPointsEarned(), accountDtos.get(0).getFamilyMembers().get(0).getPointsEarned());
        assertEquals(accounts.get(0).getFamilyMembers().get(0).getMoneyEarned(), accountDtos.get(0).getFamilyMembers().get(0).getMoneyEarned());

        assertEquals(1, accountDtos.get(0).getFamilyMembers().get(0).getRoles().size());
        assertTrue(accountDtos.get(0).getFamilyMembers().get(0).getRoles().contains("user"));

        // Account 2
        assertEquals(accounts.get(1).getFamilyName(), accountDtos.get(1).getFamilyName());
        assertEquals(accounts.get(1).getId(), accountDtos.get(1).getId());
        assertEquals(accounts.get(1).getOwner().getId(), accountDtos.get(1).getOwner().getId());
        assertEquals(accounts.get(1).getOwner().getFirstName(), accountDtos.get(1).getOwner().getFirstName());
        assertEquals(accounts.get(1).getOwner().getLastName(), accountDtos.get(1).getOwner().getLastName());
        assertEquals(accounts.get(1).getOwner().getBirthDate().toString("MM/dd/YYYY"), accountDtos.get(1).getOwner().getBirthDate());
        assertEquals(accounts.get(1).getOwner().getMoneyEarned(), accountDtos.get(1).getOwner().getMoneyEarned());
        assertEquals(accounts.get(1).getOwner().getPointsEarned(), accountDtos.get(1).getOwner().getPointsEarned());

        assertEquals(1, accountDtos.get(1).getFamilyMembers().size());
        assertEquals(accounts.get(1).getFamilyMembers().get(0).getFirstName(), accountDtos.get(1).getFamilyMembers().get(0).getFirstName());
        assertEquals(accounts.get(1).getFamilyMembers().get(0).getLastName(), accountDtos.get(1).getFamilyMembers().get(0).getLastName());
        assertEquals(accounts.get(1).getFamilyMembers().get(0).getBirthDate().toString("MM/dd/YYYY"), accountDtos.get(1).getFamilyMembers().get(0).getBirthDate());
        assertEquals(accounts.get(1).getFamilyMembers().get(0).getPointsEarned(), accountDtos.get(1).getFamilyMembers().get(0).getPointsEarned());
        assertEquals(accounts.get(1).getFamilyMembers().get(0).getMoneyEarned(), accountDtos.get(1).getFamilyMembers().get(0).getMoneyEarned());

        assertEquals(1, accountDtos.get(1).getFamilyMembers().get(0).getRoles().size());
        assertTrue(accountDtos.get(1).getFamilyMembers().get(0).getRoles().contains("user"));
    }

    @Test
    public void testToDtos_Null(){

        List<AccountDto> accountDtos = accountTranslator.toDtos(null);
        assertTrue(accountDtos.isEmpty());
    }

    @Test
    public void testToDtos_EmptyList(){

        List<AccountDto> accountDtos = accountTranslator.toDtos(new ArrayList<>());
        assertTrue(accountDtos.isEmpty());
    }
}
