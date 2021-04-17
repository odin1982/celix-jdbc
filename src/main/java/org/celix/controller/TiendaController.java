package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.TiendaModel;
import org.celix.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tienda")
public class TiendaController {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	private TiendaService tiendaService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<TiendaModel> tiendas = tiendaService.findAll();
		model.addAttribute("tiendas",tiendas);
		log.info("Tiendas encontradas ----> {}",tiendas);
		return "tienda/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		TiendaModel tienda = new TiendaModel();
		model.addAttribute("tienda",tienda);
		return "tienda/create";
	}
	
	@PostMapping("/save")
	public String save(TiendaModel tienda,RedirectAttributes redirect) {
		if(tienda.getId() == null) {
			log.info("Guardando tienda ----> {}",tienda);
			tiendaService.save(tienda);
			redirect.addFlashAttribute("message", "Se ha registrado correctamente la tienda.");
		}else {
			log.info("Editando tienda ----> {}",tienda);
			tiendaService.update(tienda);
			redirect.addFlashAttribute("message", "Se ha actualizado correctamente la tienda.");
		}
		return "redirect:/tienda/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		log.info("Editar tienda con id ----> {}",id);
		TiendaModel tienda = tiendaService.findById(id);
		model.addAttribute("tienda", tienda);
		return "tienda/create";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		log.info("Borrando tienda id ----> {}",id);
		tiendaService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente la tienda.");
		return "redirect:/tienda/index";
	}
	
}
