package com.learning.java8.example.app.lambda.collections.utils;

import com.learning.java8.example.app.lambda.collections.dto.OperacionDTO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.learning.java8.example.app.lambda.collections.utils.CollectionConvinience.asSet;


public final class Constants implements Serializable {

    private static final long serialVersionUID = -7009100314561253064L;



    /**
     * CONSTANTES.
     */

    public static final Long ID_OP_CAJA_COBRO_VENTA_PUBLICA = 2L;
    public static final Long ID_OP_CAJA_COBRO_VENTA_PRIVADA = 3L;
    public static final Long ID_OP_CAJA_PAGO_COMPRA_PUBLICA = 6L;
    public static final Long ID_OP_CAJA_PAGO_COMPRA_PRIVADA = 7L;



    public static final Set<Long> IDS_OP_VENTAS = asSet(
            ID_OP_CAJA_COBRO_VENTA_PUBLICA,
            ID_OP_CAJA_COBRO_VENTA_PRIVADA
    );

    public static final Set<Long> IDS_OP_COMPRAS = asSet(
            ID_OP_CAJA_PAGO_COMPRA_PUBLICA,
            ID_OP_CAJA_PAGO_COMPRA_PRIVADA
    );

    public static List<OperacionDTO> listaOperaciones = Arrays.asList(
            new OperacionDTO(2, "VPU", "Venta Pública"),
            new OperacionDTO(3, "VPR", "Venta Privada"),
            new OperacionDTO(6, "CPU", "Compra Pública"),
            new OperacionDTO(7, "CPR", "Compra Privada")
    );

}
