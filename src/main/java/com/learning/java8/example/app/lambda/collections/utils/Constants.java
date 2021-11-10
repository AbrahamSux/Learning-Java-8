package com.learning.java8.example.app.lambda.collections.utils;

import java.io.Serializable;
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

}
