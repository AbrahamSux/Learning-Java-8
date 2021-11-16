package com.learning.java8.example.app.rxjava;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RxApp {

    private static final Logger LOGGER = LogManager.getLogger(RxApp.class);


    private List<Integer> listOne;
    private List<Integer> listTwo;



    // MAIN
    public static void main(String[] args) {
        RxApp tester = new RxApp();

        tester.process();

    }


    // CONSTRUCTOR

    public RxApp() {
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();

        this.llenarListas();
    }



    // METHODS
    public void process() {
        List<String> listNames = new ArrayList<>();
        listNames.add("Yelena");
        listNames.add("Dayana");
        listNames.add("Abraham");
        listNames.add("Diana");

        LOGGER.info("Lista de Nombres : {}", Arrays.toString(listNames.toArray()));

        // Se recorre la lista
        recorrerUnaLista(listNames);

        // Se hace unión de 2 lista y se busca un elemento de manera Asíncrona
        buscarElementoEnUnionDeListas();
    }

    public void llenarListas() {
        for (int i = 0; i < 10; i++) {
            listOne.add(i);
            listTwo.add(i);
        }
    }

    public void recorrerUnaLista(List<String> list) {

        LOGGER.info(">>> Recorriendo Una Lista...");
        Observable<String> observable = Observable.fromArray(list.stream().toArray(String[]::new));

        // Opción 1 : se recorre la lista de manera Síncrona
        /* observable.subscribe(element -> LOGGER.info("Elemento : {}", element));*/

        // Opción 2 : se recorre la lista de manera Síncrona
        observable.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String value) {
                LOGGER.info("Elemento : {}", value);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LOGGER.error("Folló al momento de procesar la información, {}", e.getMessage());
            }

            @Override
            public void onComplete() {
                LOGGER.info("Proceso de Recorrido Finalizado con éxito!");
            }
        });
    }

    public void buscarElementoEnUnionDeListas() {
        LOGGER.info(">>> Recorriendo Unión de Dos Listas...");

        Observable<Integer> observableOne = Observable.fromArray(listOne.stream().toArray(Integer[]::new));
        Observable<Integer> observableTwo = Observable.fromArray(listTwo.stream().toArray(Integer[]::new));


        // Opción 1 : Se recorren las 2 listas de manera Asíncrona
        /*Observable.merge(observableOne, observableTwo).subscribe(new DisposableObserver<Integer>() {
            @Override
            public void onNext(@NonNull Integer value) {
                if (value == 1) {
                    LOGGER.info("Elemento : {}", value);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LOGGER.error("Folló al momento de procesar la información, {}", e.getMessage());
            }

            @Override
            public void onComplete() {
                LOGGER.info("Proceso de Merge Finalizado con éxito!");
            }
        });*/


        // Opción 2 : Se recorren las 2 listas de manera Asíncrona
        Observable.merge(observableOne, observableTwo).filter(value -> value == 1).subscribe(e -> {
            LOGGER.info("Elemento : {}", e);
        });
    }

}
