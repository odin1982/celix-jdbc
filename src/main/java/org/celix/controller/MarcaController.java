package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.MarcaModel;
import org.celix.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<MarcaModel> marcas = marcaService.findAll();
		logger.info("Marcas ----> \n{}",marcas);
		model.addAttribute("marcas", marcas);
		return "marca/index";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		logger.info("Delete marca with id ----> {}",id);
		marcaService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente la marca.");
		return "redirect:/marca/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		MarcaModel marca = new MarcaModel();
		model.addAttribute("marca", marca);
		return "marca/create";
	}
	
	@PostMapping("/save")
	public String save(MarcaModel marca,RedirectAttributes redirect) {
		if(marca.getId() == null) {
			logger.info("Saving marca ----> {}",marca);
			marcaService.save(marca);
			redirect.addFlashAttribute("message","Se ha registrado correctamente la marca");
		}else {
			logger.info("Updating marca ----> {}",marca);
			marcaService.update(marca);
			redirect.addFlashAttribute("message","Se ha actualizado correctamente la marca");
		}
		return "redirect:/marca/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		logger.info("Editing id marca ----> {}",id);
		MarcaModel marca = marcaService.findById(id);
		model.addAttribute("marca", marca);
		return "marca/create";
	}
	
}
