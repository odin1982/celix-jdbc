package org.celix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("operacion/compras")
public class ComprasController {
	
	@GetMapping("/index")
	public String index() {
		return "operacion/compras/index";
	}
}
