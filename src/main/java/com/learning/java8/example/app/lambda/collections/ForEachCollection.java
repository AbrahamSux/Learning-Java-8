package com.learning.java8.example.app.lambda.collections;

import com.learning.java8.example.app.lambda.collections.dto.OperacionDTO;
import com.learning.java8.example.app.lambda.collections.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

    public void llenarLista() {
        listaOperaciones = Constants.listaOperaciones;
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

}
