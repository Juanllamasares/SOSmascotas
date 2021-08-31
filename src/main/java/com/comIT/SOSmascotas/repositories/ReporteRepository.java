package com.comIT.SOSmascotas.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comIT.SOSmascotas.entidades.Reporte;
@Repository
@Transactional
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

	List<Reporte> reportes = new ArrayList<>();
	
	public default List<Reporte> findAllNonActive() {
		return reportes.stream().filter(p -> !p.getActivo()).collect(Collectors.toList());
	}

	public default List<Reporte> findAllActive() {
		return reportes.stream().filter(p -> p.getActivo()).collect(Collectors.toList());
	}
}
