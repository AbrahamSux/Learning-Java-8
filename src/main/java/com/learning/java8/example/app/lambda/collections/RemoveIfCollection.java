package com.learning.java8.example.app.lambda.collections;

import com.learning.java8.example.app.dto.OperacionDTO;
import com.learning.java8.example.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class RemoveIfCollection {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveIfCollection.class);

    private List<OperacionDTO> listaOperaciones;

    // MAIN.
    public static void main(String[] args) {
        RemoveIfCollection tester = new RemoveIfCollection();

        tester.procesar();
    }



    // MÉTODOS.

    public void procesar() {
        llenarLista();

        // Using RemoveIf.
        listaOperaciones = usingRemoveIf(listaOperaciones);


        listaOperaciones.forEach(operacion -> LOGGER.info("Operación : {}", operacion.toString()));
    }

    public void llenarLista() {
        listaOperaciones = new ArrayList<>();
        listaOperaciones.addAll(Constants.listOfOperations);
    }

    public List<OperacionDTO> usingRemoveIf(List<OperacionDTO> list) {

        // Using Java 7: Option 1
        /*List<OperacionDTO> listToRemoveOne = new ArrayList<>();
        for (OperacionDTO dto : list) {
            if (!Constants.IDS_OP_COMPRAS.contains(dto.getId())) {
                listToRemoveOne.add(dto);
            }
        }
        list.removeAll(listToRemoveOne);*/


        // Using Java 7: Option 2
        /*List<OperacionDTO> listToRemoveTwo = new ArrayList<>(list);
        for (OperacionDTO dto : list) {
            if (!Constants.IDS_OP_COMPRAS.contains(dto.getId())) {
                listToRemoveTwo.remove(dto);
            }
        }
        list = listToRemoveTwo;*/


        // Using Java 7: Option 3
        /*Iterator<OperacionDTO> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!Constants.IDS_OP_COMPRAS.contains(iterator.next().getId())) {
                iterator.remove();
            }
        }*/


        // Using Java 8: Lambda Expression
        list.removeIf(element -> !Constants.IDS_OP_VENTAS.contains(element.getId()));

        return list;
    }

}
