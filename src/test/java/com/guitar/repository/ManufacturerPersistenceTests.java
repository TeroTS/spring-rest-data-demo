package com.guitar.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.guitar.Main;
import com.guitar.model.Manufacturer;
import com.guitar.repository.ManufacturerJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class ManufacturerPersistenceTests {

	@Autowired
	private ManufacturerJpaRepository manufacturerJpaRepository;

	@Test
	public void testGetManufacturersFoundedBeforeDate() throws Exception {
		List<Manufacturer> mans = manufacturerJpaRepository.findByFoundedDateBefore(new Date());
		assertEquals(2, mans.size());
	}

	@Test
	public void testTrueFalse() throws Exception {
		List<Manufacturer> mans = manufacturerJpaRepository.findByActiveTrue();
		assertEquals("Fender Musical Instruments Corporation", mans.get(0).getName());

		mans = manufacturerJpaRepository.findByActiveFalse();
		assertEquals("Gibson Guitar Corporation", mans.get(0).getName());
    }

}
