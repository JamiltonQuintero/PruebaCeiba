package com.ceiba.springmvc.domain.repository;


import com.ceiba.springmvc.domain.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
