package com.comIT.SOSmascotas.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comIT.SOSmascotas.entidades.Contacto;
@Repository
@Transactional
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
