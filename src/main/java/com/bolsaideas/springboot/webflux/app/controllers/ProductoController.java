package com.bolsaideas.springboot.webflux.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsaideas.springboot.webflux.app.models.dao.ProductoDao;
import com.bolsaideas.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@Controller
public class ProductoController {

	@Autowired
	private ProductoDao productoDao;

	@GetMapping({ "/listar", "/" })
	public String listar(Model model) {
		Flux<Producto> productos = productoDao.findAll();

		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");
		return "listar";
	}
}
