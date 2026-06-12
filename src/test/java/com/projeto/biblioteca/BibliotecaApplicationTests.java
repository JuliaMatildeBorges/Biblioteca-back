package com.projeto.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import java.util.List;

@SpringBootTest
class BibliotecaApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
		System.out.println("=== JPA ENTITIES ===");
		for (EntityType<?> entity : entityManager.getMetamodel().getEntities()) {
			System.out.println("Entity: " + entity.getName() + " (Java type: " + entity.getJavaType().getName() + ")");
		}

		System.out.println("=== DB TABLES ===");
		try {
			List<String> tables = jdbcTemplate.queryForList("SHOW TABLES", String.class);
			if (tables.isEmpty()) {
				System.out.println("No tables found in the database!");
			} else {
				for (String table : tables) {
					System.out.println("Table: " + table);
				}
			}
		} catch (Exception e) {
			System.out.println("Error querying tables: " + e.getMessage());
		}
	}

}
