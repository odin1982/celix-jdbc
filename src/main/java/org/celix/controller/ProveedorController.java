package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.ProveedorModel;
import org.celix.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<ProveedorModel> proveedores = proveedorService.findAll();
		logger.info("Proveedores ----> \n{}",proveedores);
		model.addAttribute("proveedores",proveedores);
		return "proveedor/index";
	}
	
	@PostMapping("/save")
	public String save(ProveedorModel proveedor,RedirectAttributes redirect) {
		if(proveedor.getId() == null) {
			logger.info("Guardando proveedor ----> {}",proveedor);
			proveedorService.save(proveedor);
			redirect.addFlashAttribute("message", "Se ha registrado correctamente el proveedor.");
		}else {
			logger.info("Editando proveedor ----> {}",proveedor);
			proveedorService.update(proveedor);
			redirect.addFlashAttribute("message", "Se ha actualizado correctamente el proveedor.");
		}
		return "redirect:/proveedor/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		ProveedorModel proveedor = new ProveedorModel();
		model.addAttribute("proveedor", proveedor);
		return "proveedor/create";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		logger.info("Borrando proveedor id ----> {}",id);
		proveedorService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el proveedor.");
		return "redirect:/proveedor/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		logger.info("Editar proveedor con id ----> {}",id);
		ProveedorModel proveedor = proveedorService.findById(id);
		model.addAttribute("proveedor", proveedor);
		return "proveedor/create";
	}
}
