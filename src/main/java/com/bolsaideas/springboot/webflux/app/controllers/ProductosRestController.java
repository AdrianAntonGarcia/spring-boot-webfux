package com.bolsaideas.springboot.webflux.app.controllers;

import com.bolsaideas.springboot.webflux.app.models.dao.ProductoDao;
import com.bolsaideas.springboot.webflux.app.models.documents.Producto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductosRestController {
    @Autowired
    private ProductoDao productoDao;

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping()
    public Flux<Producto> index() {
        Flux<Producto> productos = productoDao.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        }).doOnNext(prod -> log.info(prod.getNombre()));
        return productos;
    }

    @GetMapping("/{id}")
    public Mono<Producto> show(@PathVariable String id) {
        // Una forma
        // Mono<Producto> producto = productoDao.findById(id);

        Mono<Producto> producto = productoDao.findAll().filter(p -> p.getId().equals(id)).next();

        return producto;
    }
}
