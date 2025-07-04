package com.perfulandia.perfulandia_venta_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.perfulandia.perfulandia_venta_api.service.VentaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.perfulandia.perfulandia_venta_api.dto.VentaDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/ventas")

public class VentaController {

    @Autowired
    private VentaService Service;
    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO nuevaVenta = Service.crearVenta(ventaDTO);
        return ResponseEntity.ok(nuevaVenta);
    }
    
    @GetMapping 
    public ResponseEntity<List<VentaDTO>> obtenerTodosVentas() {
        List<VentaDTO> ventas = Service.obtenerVentas();
        return ResponseEntity.ok(ventas);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVentaPorId(@RequestParam Integer id) {
        return Service.obtenerVentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@RequestParam Integer id, @RequestBody VentaDTO ventaDTO) {
        return Service.actualizarVenta(id, ventaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@RequestParam Integer id) {
        if (Service.eliminarVenta(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hateoas/{id}")
    public VentaDTO obtenerHATEOAS(@PathVariable Integer id) {
        VentaDTO dto = Service.obtenerVentaPorId(id)
                .orElseThrow(() -> new RuntimeException("Venta not found with id: " + id));
        
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    
    @GetMapping("/hateoas")
    public List<VentaDTO> obtenerTodosHATEOAS() {
        List<VentaDTO> lista = Service.obtenerVentas();

        for (VentaDTO dto : lista) {
            
            dto.add(Link.of("http://localhost:8888/api/proxy/productos").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
    

}
