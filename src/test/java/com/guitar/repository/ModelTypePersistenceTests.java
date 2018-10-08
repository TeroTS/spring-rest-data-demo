package com.guitar.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.Main;
import com.guitar.model.ModelType;
import com.guitar.repository.ModelTypeJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class ModelTypePersistenceTests {
	@Autowired
	private ModelTypeJpaRepository modelTypeJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		ModelType mt = new ModelType();
		mt.setName("Test Model Type");
		mt = modelTypeJpaRepository.save(mt);

		ModelType otherModelType = modelTypeJpaRepository.findById(mt.getId()).orElse(null);
		assertEquals("Test Model Type", otherModelType.getName());
		
		modelTypeJpaRepository.delete(otherModelType);
	}

	@Test
	public void testFind() throws Exception {
		ModelType mt = modelTypeJpaRepository.findById(1L).orElse(null);
		assertEquals("Dreadnought Acoustic", mt.getName());
	}

	@Test
	public void testForNull() throws Exception {
		List<ModelType> mts = modelTypeJpaRepository.findByNameIsNull();
		assertNull(mts.get(0).getName());
	}
}
