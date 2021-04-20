package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.TipoProductoModel;
import org.celix.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipo-producto")
public class TipoProductoController {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	
	@Autowired
	private TipoProductoService tipoProductoService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<TipoProductoModel> tiposProducto = tipoProductoService.findAll();
		log.info("Tipos de producto encontrados:{}",tiposProducto);
		model.addAttribute("tiposProducto",tiposProducto);
		return "tipoproducto/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		TipoProductoModel tipoProducto = new TipoProductoModel();
		model.addAttribute("tipoProducto", tipoProducto);
		return "tipoproducto/create";
	}
	
	@PostMapping("/save")
	public String save(TipoProductoModel tipoProducto,RedirectAttributes redirect) {
		if(tipoProducto.getId() == null) {
			log.info("Guardando tipo producto ----> {}",tipoProducto);
			tipoProductoService.save(tipoProducto);
			redirect.addFlashAttribute("message","Se ha registrado correctamente el tipo de producto");
		}else {
			log.info("Actualizando tipo producto ----> {}",tipoProducto);
			tipoProductoService.update(tipoProducto);
			redirect.addFlashAttribute("message","Se ha actualizado correctamente el tipo de producto");
		}
		return "redirect:/tipo-producto/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		log.info("Editando tipo producto ----> {}",id);
		TipoProductoModel tipoProducto = tipoProductoService.findById(id);
		model.addAttribute("tipoProducto", tipoProducto);
		return "tipoproducto/create";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		log.info("Delete tipo producto with id ----> {}",id);
		tipoProductoService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el tipo de producto.");
		return "redirect:/tipo-producto/index";
	}

}
