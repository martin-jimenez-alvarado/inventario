package com.home.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.home.demo.entity.Producto;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
@DataJpaTest
public class ProductoRepositoryTest {
	@Autowired
	IProductoRepositorio repoProducto;

	@Test
	public void createProducto() {
		Producto producto = repoProducto.save(new Producto("Shampoo", 12.8f));
		assertEquals(producto.getNombre(), "Shampoo");
	}
}
