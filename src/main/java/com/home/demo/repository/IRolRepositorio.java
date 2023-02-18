package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.home.demo.entity.Rol;


@Repository
public interface IRolRepositorio extends JpaRepository<Rol, Integer>{
	
}
