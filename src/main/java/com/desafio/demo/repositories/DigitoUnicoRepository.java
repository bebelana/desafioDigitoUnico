package com.desafio.demo.repositories;

import com.desafio.demo.models.DigitoUnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitoUnicoRepository extends JpaRepository<DigitoUnico,Integer> {
}
