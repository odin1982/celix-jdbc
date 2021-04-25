package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.TipoDocumentoModel;
import org.celix.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipo-documento")
public class TipoDocumentoController {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<TipoDocumentoModel> tiposDocumento = tipoDocumentoService.findAll();
		log.info("Tipos de documento encontrados:{}",tiposDocumento);
		model.addAttribute("tiposDocumento",tiposDocumento);
		return "tipodocumento/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		TipoDocumentoModel tipoDocumento = new TipoDocumentoModel();
		model.addAttribute("tipoDocumento", tipoDocumento);
		return "tipodocumento/create";
	}
	
	@PostMapping("/save")
	public String save(TipoDocumentoModel tipoDocumento,RedirectAttributes redirect) {
		if(tipoDocumento.getId() == null) {
			log.info("Guardando tipo documento ----> {}",tipoDocumento);
			tipoDocumentoService.save(tipoDocumento);
			redirect.addFlashAttribute("message","Se ha registrado correctamente el tipo de documento");
		}else {
			log.info("Actualizando tipo documento ----> {}",tipoDocumento);
			tipoDocumentoService.update(tipoDocumento);
			redirect.addFlashAttribute("message","Se ha actualizado correctamente el tipo de documento");
		}
		return "redirect:/tipo-documento/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		log.info("Editando tipo documento ----> {}",id);
		TipoDocumentoModel tipoDocumento = tipoDocumentoService.findById(id);
		model.addAttribute("tipoDocumento", tipoDocumento);
		return "tipodocumento/create";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		log.info("Borrar tipo documento con id ----> {}",id);
		tipoDocumentoService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el tipo de documento.");
		return "redirect:/tipo-documento/index";
	}

}
