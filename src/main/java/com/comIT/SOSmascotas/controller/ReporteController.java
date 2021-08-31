package com.comIT.SOSmascotas.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comIT.SOSmascotas.entidades.Reporte;
import com.comIT.SOSmascotas.repositories.ReporteRepository;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private ReporteRepository reporteRepo;


	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@RequestMapping("/about")
	public String about() {
		return "about";
	}

	// devuelve un listado de reportes
	@RequestMapping("/listado")
	public String list(Model model) {
		model.addAttribute("reportes", reporteRepo.findAll());
		return "listado";
	}

	// crea un reporte
	@RequestMapping("/crear")
	public String crear(Model model) {
		Reporte reporte = new Reporte();
		reporte.setFechaCreacion(new Date());
		model.addAttribute("reporte", reporte);

		return "crear";
	}

	// guarda un reporte
	@PostMapping(value = "/guardarReporte")
	public String guardarReporte(@ModelAttribute Reporte rep, Model model) {

		Date fecha = new Date();

		rep.setFechaCreacion(fecha);
		reporteRepo.save(rep);

		model.addAttribute("reportes", reporteRepo.findAll());
		return "listado";
	}

	// borra un reporte por id
	@PostMapping(value = "/borrar/{id}")
	public String borrarReporte(@PathVariable(value = "id") Long id, Model model) {

		Reporte rep = reporteRepo.findById(id).get();
		model.addAttribute("reporte", rep);

		try {
			reporteRepo.deleteById(id);
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo eliminar el reporte");
			return "error";
		}

		model.addAttribute("reportes", reporteRepo.findAll());
		return "listado";
	}

	@GetMapping(value = "/error")
	public String error() {
		return "error";
	}

}
