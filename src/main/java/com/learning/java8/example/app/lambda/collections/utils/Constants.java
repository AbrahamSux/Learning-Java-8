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

    public static final Long ID_OP_CAJA_COBRO_VENTA_AL_PUBLICO = 2L;
    public static final Long ID_OP_CAJA_COBRO_DE_VENTA_CON_BILLETE = 3L;



    public static final Set<Long> IDS_OP_VENTAS = asSet(
            ID_OP_CAJA_COBRO_VENTA_AL_PUBLICO,
            ID_OP_CAJA_COBRO_DE_VENTA_CON_BILLETE
    );

    public static List<OperacionDTO> listaOperaciones = Arrays.asList(
            new OperacionDTO(1, "PP", "Pagar Prestamo"),
            new OperacionDTO(2, "VP", "Venta al Publico"),
            new OperacionDTO(3, "VB", "Venta con Billete"),
            new OperacionDTO(4, "CV", "Venta con Billete para Empeno"),
            new OperacionDTO(5, "QE", "Liquidacion por Reempeno"),
            new OperacionDTO(6, "DS", "Cobro Desempeño"),
            new OperacionDTO(7, "RP", "Recepcion Pago Referenciado"),
            new OperacionDTO(8, "RF", "Cobro Refrendo"),
            new OperacionDTO(9, "CI", "Cobro Reimpresion Billete"),
            new OperacionDTO(10, "RB", "Cobro Reposicion Billete"),
            new OperacionDTO(11, "BA", "Cobro de Cargo Anticip,ado a Valuador"),
            new OperacionDTO(12, "MP", "Marcar como Separ,ada"),
            new OperacionDTO(13, "DC", "Caducar Demasia,"),
            new OperacionDTO(14, "PD", "Pagar Demasia"),
            new OperacionDTO(15, "GA", "Cobro Almacenaje"),
            new OperacionDTO(16, "AD", "Acreditacion de D,esempeno Banco"),
            new OperacionDTO(17, "AS", "Servicio Avaluo"),
            new OperacionDTO(18, "AX", "Acreditacion de Dese,mpeno Cruzado"),
            new OperacionDTO(19, "BS", "Baja por Siniestro"),
            new OperacionDTO(20, "PI", "Pago de Indemnizacion")
    );

}
