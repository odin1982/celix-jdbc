package org.celix.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celix.model.MarcaModel;
import org.celix.model.ProductoModel;
import org.celix.model.ProveedorModel;
import org.celix.model.TipoProductoModel;
import org.celix.service.MarcaService;
import org.celix.service.ProductoService;
import org.celix.service.ProveedorService;
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
@RequestMapping("/producto")
public class ProductoController {
	private final Logger log = LogManager.getLogger(this.getClass());
	@Autowired private ProductoService productoService;
	@Autowired private TipoProductoService tipoProductoService;
	@Autowired private MarcaService marcaService;
	@Autowired private ProveedorService proveedorService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<ProductoModel> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		return "producto/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		ProductoModel producto = new ProductoModel();
		List<TipoProductoModel> tiposProducto = tipoProductoService.findAll();
		List<MarcaModel> marcas = marcaService.findAll();
		List<ProveedorModel> proveedores = proveedorService.findAll();
		model.addAttribute("producto", producto);
		model.addAttribute("tiposProducto", tiposProducto);
		model.addAttribute("marcas", marcas);
		model.addAttribute("proveedores", proveedores);
		return "producto/create";
	}
	
	@PostMapping("/save")
	public String save(ProductoModel producto,RedirectAttributes redirect) {
		if(producto.getId() == null) {
			log.info("Guardando producto ----> {}",producto);
			productoService.save(producto);
			redirect.addFlashAttribute("message", "Se ha registrado correctamente el producto.");
		}else {
			log.info("Editando producto ----> {}",producto);
			productoService.update(producto);
			redirect.addFlashAttribute("message", "Se ha actualizado correctamente el producto.");
		}
		return "redirect:/producto/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		log.info("Editar producto con id ----> {}",id);
		
		ProductoModel producto = productoService.findById(id);
		List<TipoProductoModel> tiposProducto = tipoProductoService.findAll();
		List<MarcaModel> marcas = marcaService.findAll();
		List<ProveedorModel> proveedores = proveedorService.findAll();
		
		model.addAttribute("producto", producto);
		model.addAttribute("tiposProducto", tiposProducto);
		model.addAttribute("marcas", marcas);
		model.addAttribute("proveedores", proveedores);
		return "producto/create";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirect) {
		log.info("Borrando producto id ----> {}",id);
		productoService.deleteById(id);
		redirect.addFlashAttribute("message", "Se ha eliminado correctamente el producto.");
		return "redirect:/producto/index";
	}
}
