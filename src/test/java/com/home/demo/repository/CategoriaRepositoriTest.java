package com.home.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.home.demo.entity.Categoria;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
@DataJpaTest
public class CategoriaRepositoriTest {
	@Autowired
	private ICategoriaRepositorio categoriaRepositorio;
	
	@Test
	public void testCrearCategoria() {
		Categoria electronicos = categoriaRepositorio.save(new Categoria("Electronicos"));
		assertThat(electronicos.getId()).isGreaterThan(0);
	}
}
