package org.celix.controller;

import java.util.List;

import org.celix.model.AlmacenModel;
import org.celix.model.ComprasModel;
import org.celix.model.ProveedorModel;
import org.celix.model.TipoDocumentoModel;
import org.celix.service.AlmacenService;
import org.celix.service.ProveedorService;
import org.celix.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("operacion/compras")
public class ComprasController {
	
	@Autowired private ProveedorService proveedorService;
	@Autowired private TipoDocumentoService tipoDocumentoService;
	@Autowired private AlmacenService almacenService;
	
	@GetMapping("/index")
	public String index(Model model) {
		ComprasModel compras = new ComprasModel();
		List<ProveedorModel> proveedores = proveedorService.findAll();
		List<TipoDocumentoModel> tiposDocumento = tipoDocumentoService.findAll();
		List<AlmacenModel> almacenes = almacenService.findAll();
		
		model.addAttribute("compras", compras);
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("tiposDocumento", tiposDocumento);
		model.addAttribute("almacenes",almacenes);
		return "operacion/compras/index";
	}
}
