package com.evgeniiavak.studyhibernate.repository;

import com.evgeniiavak.studyhibernate.model.AbstractChildEntity;
import com.evgeniiavak.studyhibernate.model.shipment.Item;
import com.evgeniiavak.studyhibernate.model.shipment.Package;
import com.evgeniiavak.studyhibernate.model.shipment.Shipment;
import com.github.javafaker.Faker;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentIdentifierBag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ShipmentRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(ShipmentRepositoryTest.class);

    @Autowired
    private ShipmentRepository shipmentRepository;


    @Test
    public void testListOneToManyJoinColumn() {
        test(
                Shipment::getPackages,
                Package::new,
                PersistentBag.class
        );
    }

    @Test
    public void testListOneToManyColumnId() {
        test(
                Shipment::getItems,
                Item::new,
                PersistentIdentifierBag.class
        );
    }

    private <T extends AbstractChildEntity> void test(Function<Shipment, Collection<T>> collectionExtractor,
                         Function<String, T> childProducer, Class<?> expectedClass) {
        Shipment shipment = new Shipment();
        Collection<T> childEntities = collectionExtractor.apply(shipment);

        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            childEntities.add(childProducer.apply(faker.name()));
        }

        logger.info("-------------------------Test create entity with collection----------------------");

        Collection<T> result = collectionExtractor.apply(shipmentRepository.save(shipment));

        assertTrue(String.format("Expected type of collection was: [%s]. But actual type was [%s]",
                expectedClass.getName(),
                result.getClass().getName()),
                expectedClass.isInstance(result));
        logger.info("----------------------------------------------------------------------------------");
        logger.info("----------------------------------------------------------------------------------");
        logger.info("----------------------------------------------------------------------------------");


        logger.info("-------------------------Test update the entity collection-------------------------");
        result.add(childProducer.apply(faker.name()));
        result = collectionExtractor.apply(shipmentRepository.save(shipment));
        logger.info("-----------------------------------------------------------------------------------");
        logger.info("-----------------------------------------------------------------------------------");
        logger.info("-----------------------------------------------------------------------------------");


        logger.info("-------------------------Test clear entity collection-------------------------");
        result.clear();
        result = collectionExtractor.apply(shipmentRepository.save(shipment));
        logger.info("-----------------------------------------------------------------------------------");
        logger.info("-----------------------------------------------------------------------------------");
        logger.info("-----------------------------------------------------------------------------------");
    }

}