package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.AlmacenModel;
import org.celix.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/almacen")
public class AlmacenController {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired private AlmacenService almacenService;

	@GetMapping("/index")
	public String index(Model model) {
		List<AlmacenModel> almacenes = almacenService.findAll();
		model.addAttribute("almacenes", almacenes);
		logger.info("Almacenes encontrados ----> {}",almacenes);
		return "almacen/index";
	}
	
	@PostMapping("/save")
	public String save(AlmacenModel almacen,BindingResult result,RedirectAttributes redirect) {
		if(almacen.getId() == null) {
			logger.info("Guardando almacen ----> {}",almacen);
			almacenService.save(almacen);
			redirect.addFlashAttribute("message", "Se ha registrado correctamente el almacén.");
		}else {
			logger.info("Editando almacen ----> {}",almacen);
			almacenService.update(almacen);
			redirect.addFlashAttribute("message", "Se ha actualizado correctamente el almacén.");
		}
		return "redirect:/almacen/index";
	}
	
	
	@GetMapping("/create")
	public String create(Model model) {
		AlmacenModel almacen = new AlmacenModel();
		model.addAttribute("almacen", almacen);
		return "almacen/create";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		logger.info("Borrando almacen id ----> {}",id);
		almacenService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el almacén.");
		return "redirect:/almacen/index";
	}

	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		logger.info("Editar almacen con id ----> {}",id);
		AlmacenModel almacen = almacenService.findById(id);
		model.addAttribute("almacen", almacen);
		return "almacen/create";
	}

}

