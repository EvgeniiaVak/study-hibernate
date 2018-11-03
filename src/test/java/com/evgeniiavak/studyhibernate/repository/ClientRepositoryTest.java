package com.evgeniiavak.studyhibernate.repository;

import com.evgeniiavak.studyhibernate.model.client.Account;
import com.evgeniiavak.studyhibernate.model.client.Address;
import com.evgeniiavak.studyhibernate.model.client.Client;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentIdentifierBag;
import org.hibernate.collection.internal.PersistentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testPersistentBag() {

        Client client = new Client();
        client.getNames().add("Alice From Wonderland");
        client.getNames().add("Alice The Adventurous");
        client.getNames().add("Alice The Adventurous");

        List<String> names = clientRepository.save(client).getNames();

        assertTrue(String.format("actual type was %s", names.getClass().getSimpleName()),
                names instanceof PersistentBag);
    }

    @Test
    public void testPersistentIdentifierBag() {

        Client client = new Client();
        client.getAccounts().add(new Account(400L));
        client.getAccounts().add(new Account(94200L));
        client.getAccounts().add(new Account(500L));
        client.getAccounts().add(new Account(600L));

        List<Account> accounts = clientRepository.save(client).getAccounts();

        assertTrue(String.format("actual type was %s", accounts.getClass().getSimpleName()),
                accounts instanceof PersistentIdentifierBag);
    }

    @Test
    public void testCollectionIgnoresOrder() {

        Client client = new Client();
        client.getAddresses().add(new Address("Temple One Street"));
        client.getAddresses().add(new Address("Temple Two Street"));
        client.getAddresses().add(new Address("Temple Three Street"));

        Collection<Address> addresses = clientRepository.save(client).getAddresses();

        assertTrue(String.format("actual type was %s", addresses.getClass().getSimpleName()),
                addresses instanceof PersistentBag);
    }

    @Test
    public void testPersistentList() {

        Client client = new Client();
        client.getOrderedAddresses().add(new Address("Round One Street"));
        client.getOrderedAddresses().add(new Address("Round Two Street"));
        client.getOrderedAddresses().add(new Address("Round Three Street"));

        List<Address> orderedAddresses = clientRepository.save(client).getOrderedAddresses();

        assertTrue(String.format("actual type was %s", orderedAddresses.getClass().getSimpleName()),
                orderedAddresses instanceof PersistentList);
    }
}