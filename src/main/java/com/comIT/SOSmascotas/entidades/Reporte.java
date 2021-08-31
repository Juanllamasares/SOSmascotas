package com.comIT.SOSmascotas.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Reporte implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date fechaCreacion;
	private Boolean activo = true;

	@Enumerated(EnumType.STRING)
	Estado estado;

	@OneToOne(cascade = CascadeType.ALL)
	Mascota mascota;
	@OneToOne(cascade = CascadeType.ALL)
	Contacto contacto;
	@OneToOne(cascade = CascadeType.ALL)
	Foto foto;
	

}
