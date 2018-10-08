package com.guitar.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.Main;
import com.guitar.model.Model;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class ModelPersistenceTests {

	@Autowired
	private ModelJpaRepository modelJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Model m = new Model();
		m.setFrets(10);
		m.setName("Test Model");
		m.setPrice(BigDecimal.valueOf(55L));
		m.setWoodType("Maple");
		m.setYearFirstMade(new Date());
		m = modelJpaRepository.save(m);

		Model otherModel = modelJpaRepository.findById(m.getId()).orElse(null);
		assertEquals("Test Model", otherModel.getName());
		assertEquals(10, otherModel.getFrets());

		//delete BC location now
		modelJpaRepository.delete(otherModel);
	}

	@Test
	public void testGetModelsInPriceRange() throws Exception {
		List<Model> mods = modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));
		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsByType() throws Exception {
		List<Model> mods = modelJpaRepository.findAllModelsByType("Electric");
		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsByTypes() throws Exception {
		List<String> types = new ArrayList<String>();
		types.add("Electric");
		types.add("Acoustic");
		List<Model> mods = modelJpaRepository.findByModelTypeNameIn(types);
		
		mods.forEach((model) -> {
			assertTrue(model.getModelType().getName().equals("Electric") || 
					model.getModelType().getName().equals("Acoustic"));
		});
	}
}
