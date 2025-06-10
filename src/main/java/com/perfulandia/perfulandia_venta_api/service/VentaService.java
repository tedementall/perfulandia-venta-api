package com.perfulandia.perfulandia_venta_api.service;

import com.perfulandia.perfulandia_venta_api.dto.VentaDTO;
import com.perfulandia.perfulandia_venta_api.model.Venta;
import com.perfulandia.perfulandia_venta_api.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository Repository;

    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        Venta venta = toEntity(ventaDTO);
        Venta ventaCreada = Repository.save(venta);
        return toDto(ventaCreada);
        
    }

    public List<VentaDTO> obtenerVentas() {
        return Repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<VentaDTO> obtenerVentaPorId(Integer id) {
        return Repository.findById(id).map(this::toDto);
    }

    public Optional<VentaDTO> actualizarVenta(Integer id, VentaDTO ventaDTO) {
        return Repository.findById(id).map(venta -> {
            venta.setIdCliente(ventaDTO.getId_Cliente());
            venta.setIdVendedor(ventaDTO.getId_Vendedor());
            venta.setFechaVenta(ventaDTO.getFecha_Venta());
            return toDto(Repository.save(venta));   
        });
    }

    public boolean eliminarVenta(Integer id) {
        if (Repository.existsById(id)) {
            Repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Metodos auxilares
    private VentaDTO toDto(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId_Venta(venta.getId_venta());
        dto.setId_Cliente(venta.getIdCliente());
        dto.setId_Vendedor(venta.getIdVendedor());
        dto.setFecha_Venta(venta.getFechaVenta());
        return dto; 

    }
        
    private Venta toEntity (VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setId_venta(ventaDTO.getId_Venta());
        venta.setIdCliente(ventaDTO.getId_Cliente());
        venta.setIdVendedor(ventaDTO.getId_Vendedor());
        venta.setFechaVenta(ventaDTO.getFecha_Venta());
        return venta;

        }
          

}