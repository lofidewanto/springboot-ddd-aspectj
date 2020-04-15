package de.crowdcode.bitemporal.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonImplIT {

	private final static Logger logger = LoggerFactory.getLogger(PersonImplIT.class);

	@Test
	void testGetAddresses() {
		Person person = new PersonImpl();
		Address address = person.address();

		logger.info("Address: " + address.getCity());
	}

}
