package com.ceiba.springmvc.domain.repository;


import com.ceiba.springmvc.domain.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepository extends JpaRepository<Libro, String>{

}
