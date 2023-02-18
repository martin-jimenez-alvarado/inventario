package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.home.demo.entity.ArticuloCarrito;

@Repository
public interface ICarritoArticuloRepositorio extends JpaRepository<ArticuloCarrito, Integer>{

}
