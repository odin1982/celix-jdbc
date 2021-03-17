package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.EstatusProductoModel;
import org.celix.service.EstatusProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estatusproducto")
public class EstatusProductoController {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private EstatusProductoService estatusProductoService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<EstatusProductoModel> estatusProductos = estatusProductoService.findAll();
		logger.info("Estatus productos ----> {}",estatusProductos);
		model.addAttribute("estatusProductos", estatusProductos);
		return "estatusproducto/index";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		logger.info("Delete estatus producto with id ----> {}",id);
		estatusProductoService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el almacén.");
		return "redirect:/estatusproducto/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		EstatusProductoModel estatusProducto = new EstatusProductoModel();
		model.addAttribute("estatusProducto", estatusProducto);
		return "estatusproducto/create";
	}
	
	@PostMapping("/save")
	public String save(EstatusProductoModel estatusProducto,RedirectAttributes redirect) {
		if(estatusProducto.getId() == null) {
			logger.info("Saving estatusProducto ----> {}",estatusProducto);
			estatusProductoService.save(estatusProducto);
			redirect.addFlashAttribute("message","Se ha registrado correctamente el estatus del producto");
		}else {
			logger.info("Updating estatusProducto ----> {}",estatusProducto);
			estatusProductoService.update(estatusProducto);
			redirect.addFlashAttribute("message","Se ha actualizado correctamente el estatus del producto");
		}
		return "redirect:/estatusproducto/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		logger.info("Editing id estatus producto ----> {}",id);
		EstatusProductoModel estatusProducto = estatusProductoService.findById(id);
		model.addAttribute("estatusProducto", estatusProducto);
		return "estatusProducto/create";
	}
	
}
