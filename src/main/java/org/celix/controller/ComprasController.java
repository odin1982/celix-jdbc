package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.AlmacenModel;
import org.celix.model.ComprasModel;
import org.celix.model.ProductoModel;
import org.celix.model.ProveedorModel;
import org.celix.model.TipoDocumentoModel;
import org.celix.service.AlmacenService;
import org.celix.service.ComprasService;
import org.celix.service.ProductoService;
import org.celix.service.ProveedorService;
import org.celix.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("operacion/compras")
public class ComprasController {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired private ProveedorService proveedorService;
	@Autowired private TipoDocumentoService tipoDocumentoService;
	@Autowired private AlmacenService almacenService;
	@Autowired private ProductoService productoService;
	@Autowired private ComprasService comprasService;
	
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
	
	@ResponseBody
	@GetMapping("buscar/producto")
	public ProductoModel existeProducto(@RequestParam("codigoProducto") String codigoProducto) {
		if(productoService.existeCodigo(codigoProducto)) {
			return productoService.findByCodigoProducto(codigoProducto);
		}else {
			return new ProductoModel();
		}
	}
	
	@PostMapping("save/compras")
	public String saveCompras(@RequestBody ComprasModel compra) {
		logger.info("compra: ",compra);
		comprasService.saveCompra(compra);
		return "redirect:operacion/compras/index";
	}
}
