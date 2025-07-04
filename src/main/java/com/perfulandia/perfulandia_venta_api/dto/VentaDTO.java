
package com.perfulandia.perfulandia_venta_api.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO extends RepresentationModel<VentaDTO> {

    private Integer id_Venta;
    private Integer id_Cliente;
    private Integer id_Vendedor;
    private Integer fecha_Venta;

    private Integer id;

    public Integer getId() {
        return id_Venta;
    }

    public void setId(Integer id) {
        this.id_Venta= id;
    }

    
}
