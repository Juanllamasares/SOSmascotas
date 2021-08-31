package com.comIT.SOSmascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.comIT.SOSmascotas.entidades.Foto;
import com.comIT.SOSmascotas.repositories.FotoRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;
@Controller
@RequestMapping("/fotos")
public class FotoController {
	
	@Autowired
	private FotoRepository repo;
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	//me devuelve el listado de fotos
	@RequestMapping("/listado")
	public String list(Model model) {
		model.addAttribute("fotos", repo.findAll());
		return "listado";
	}
	
	//crea una foto
	@RequestMapping("/crear")
	public String crear(Model model) {
	     Foto foto = new Foto();
		foto.setFoto(new String());
		model.addAttribute("foto", foto);

		return "crear";
	}
	//guarda una foto
	@RequestMapping(value = "/guardarFoto", method = { RequestMethod.POST, RequestMethod.PUT })
	public String guardarFoto(@RequestParam("foto") MultipartFile file,Model model) throws ParseException {

		
	    Foto foto = new Foto();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("no es una imagen valida");
		}
		try {
			foto.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        repo.save(foto);
        return "guardar";
		}
	//borra una foto por id
	@PostMapping(value = "/borrar/{id}")
	public String fotoBorrada(@PathVariable(value = "id") long id, Model model) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo eliminar la foto");
			return "error";
		}
		return "foto borrada";
	}
	
	@GetMapping(value = "/error")
	public String error() {
		return "error";
	}

}
