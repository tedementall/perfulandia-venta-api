package com.perfulandia.perfulandia_venta_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "venta")
@Data 

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venta;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Column(name = "fecha_venta")
    private Integer fechaVenta;

    
}
