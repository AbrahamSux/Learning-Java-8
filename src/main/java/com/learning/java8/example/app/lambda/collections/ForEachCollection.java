package com.learning.java8.example.app.lambda.collections;

import com.learning.java8.example.app.lambda.collections.dto.OperacionDTO;
import com.learning.java8.example.app.lambda.collections.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ForEachCollection {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForEachCollection.class);

    private List<OperacionDTO> listaOperaciones;

    // MAIN.
    public static void main(String[] args) {
        ForEachCollection tester = new ForEachCollection();

        tester.procesar();
    }



    // MÉTODOS.

    public void procesar() {

        llenarLista();

        // Using ForEach.
        /*listaOperaciones = usingForEach(listaOperaciones);*/


        // Using Lambda Expression and Predicate.
        // Predicate<OperacionDTO> predicate = op IN (2, 3)
        listaOperaciones = evalue(listaOperaciones, (OperacionDTO op) -> Constants.IDS_OP_VENTAS.contains(op.getId()));


        listaOperaciones.forEach(operacion -> LOGGER.info("Operación : {}", operacion.toString()));
    }

    public List<OperacionDTO> usingForEach(List<OperacionDTO> list) {
        List<OperacionDTO> operacions = new ArrayList<>();

        // Using Java 7
        /*for(OperacionDTO dto : list) {
            if (Constants.IDS_OP_VENTAS.contains(dto.getId())) {
                operacions.add(dto);
            }
        }*/

        // Using Java 8: Lambda Expression
        list.forEach(dto -> {
            if (Constants.IDS_OP_VENTAS.contains(dto.getId())) {
                operacions.add(dto);
            }
        });

        return operacions;
    }

    public List<OperacionDTO> evalue(List<OperacionDTO> list, Predicate<OperacionDTO> predicate) {
        List<OperacionDTO> operacions = new ArrayList<>();

        // Using Java 7
        /*for (OperacionDTO value : list) {
            if (predicate.test(value)) {
                operacions.add(value);
            }
        }*/

        // Using Java 8: Lambda Expression and Predicate
        list.forEach(element -> {
            if(predicate.test(element)) {
                operacions.add(element);
            }
        });

        return operacions;
    }



    public void llenarLista() {
        listaOperaciones = Arrays.asList(
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

}
