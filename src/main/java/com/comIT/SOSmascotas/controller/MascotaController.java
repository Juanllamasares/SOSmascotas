package com.comIT.SOSmascotas.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comIT.SOSmascotas.entidades.Mascota;
import com.comIT.SOSmascotas.repositories.MascotaRepository;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {
	@Autowired
	private MascotaRepository repo;
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@RequestMapping("/about")
	public String about() {
		return "about";
	}

    //devuelve un listado de mascotas 
	@RequestMapping("/listado")
	public String list(Model model) {
		model.addAttribute("mascotas", repo.findAll());
		return "listado";
	}
	
	//crea una mascota
		@RequestMapping("/crear")
		public String crear(Model model) {
			Mascota mascota = new Mascota();
			mascota.setUbicacion(new String());
			model.addAttribute("mascota", mascota);

			return "crear";
		}
    //guarda una mascota
	@RequestMapping(value = "/guardarMascota", method = { RequestMethod.POST, RequestMethod.PUT })
	public String guardarMascota(@RequestParam(value = "descripcion") String descripcion, @RequestParam(value = "ubicacion") String ubicacion,
			Model model) throws ParseException {

		Mascota mascota = new Mascota();
		mascota.setDescripcion(new String());
		mascota.setUbicacion(new String());
		repo.save(mascota);
		model.addAttribute("mascota", mascota);
		return "redirect:/listado";
	}
    //borra una mascota por id
    @PostMapping(value = "/borrar/{id}")
	public String mascotaBorrada(@PathVariable(value = "id") long id, Model model) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo eliminar la mascota");
			return "error";
		}
		return "mascota borrada";
	}

	@GetMapping(value = "/error")
	public String error() {
		return "error";
	}

}
