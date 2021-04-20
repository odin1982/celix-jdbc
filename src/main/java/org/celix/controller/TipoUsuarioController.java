package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.TipoUsuarioModel;
import org.celix.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipo-usuario")
public class TipoUsuarioController {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<TipoUsuarioModel> tiposUsuario = tipoUsuarioService.findAll();
		log.info("Tipos de usuario encontrados:{}",tiposUsuario);
		model.addAttribute("tiposUsuario",tiposUsuario);
		return "tipousuario/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		TipoUsuarioModel tipoUsuario = new TipoUsuarioModel();
		model.addAttribute("tipoUsuario", tipoUsuario);
		return "tipousuario/create";
	}
	
	@PostMapping("/save")
	public String save(TipoUsuarioModel tipoUsuario,RedirectAttributes redirect) {
		if(tipoUsuario.getId() == null) {
			log.info("Guardando tipo usuario ----> {}",tipoUsuario);
			tipoUsuarioService.save(tipoUsuario);
			redirect.addFlashAttribute("message","Se ha registrado correctamente el tipo de usuario");
		}else {
			log.info("Actualizando tipo usuario ----> {}",tipoUsuario);
			tipoUsuarioService.update(tipoUsuario);
			redirect.addFlashAttribute("message","Se ha actualizado correctamente el tipo de usuario");
		}
		return "redirect:/tipo-usuario/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		log.info("Editando tipo usuario ----> {}",id);
		TipoUsuarioModel tipoUsuario = tipoUsuarioService.findById(id);
		model.addAttribute("tipoUsuario", tipoUsuario);
		return "tipousuario/create";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		log.info("Borrar tipo usuario con id ----> {}",id);
		tipoUsuarioService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el tipo de usuario.");
		return "redirect:/tipo-usuario/index";
	}

}
