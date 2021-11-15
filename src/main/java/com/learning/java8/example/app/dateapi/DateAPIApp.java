package com.learning.java8.example.app.dateapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateAPIApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateAPIApp.class);



    // MAIN
    public static void main(String[] args) throws InterruptedException {
        DateAPIApp tester = new DateAPIApp();

        tester.procesar();
    }



    // METHODS

    public void procesar() throws InterruptedException {
        LOGGER.info(">>> Comparando Fechas...");
        comparandoFechas();

        LOGGER.info(">>> Comparando Horas...");
        comparandoTiempos();

        LOGGER.info(">>> Comparando Fecha y Hora...");
        compareDateAndTime();

        LOGGER.info(">>> Midiendo tiempos...");
        medirTiempo();

        LOGGER.info(">>> Periodo Entre Fechas...");
        periodoEntreFechas(8);

        LOGGER.info(">>> Convirtiendo Fechas...");
        convertirFecha(8);
    }

    public void comparandoFechas() {

        // Using Java 7
        /*Calendar citaProgramada = Calendar.getInstance();
        Calendar irACitaHoy = Calendar.getInstance();
        citaProgramada.set(2021, 12, 05);

        LOGGER.info("Hoy está después de la Cita Programada? : Hoy({}) > Cita({}) = {}",
                irACitaHoy.getTime(), citaProgramada.getTime(), irACitaHoy.after(citaProgramada));
        LOGGER.info("Hoy está antes de la Cita Programada? : Hoy({}) < Cita({}) = {}",
                irACitaHoy.getTime(), citaProgramada.getTime(), irACitaHoy.before(citaProgramada));
        */


        // Using java 8
        LocalDate citaProgramada = LocalDate.of(2021, 12, 5);
        LocalDate irACitaHoy = LocalDate.now();

        LOGGER.info("Hoy está después de la Cita Programada? : Hoy({}) > Cita({}) = {}",
                irACitaHoy, citaProgramada, irACitaHoy.isAfter(citaProgramada));
        LOGGER.info("Hoy está antes de la Cita Programada? : Hoy({}) < Cita({}) = {}",
                irACitaHoy, citaProgramada, irACitaHoy.isBefore(citaProgramada));

    }

    public void comparandoTiempos() {
        LocalTime citaProgramada = LocalTime.of(18, 30, 0);
        LocalTime irACitaAhorita = LocalTime.now();

        LOGGER.info("Justo ahora está después de la Cita Programada? : Hora Actual({}) > Cita({}) = {}",
                irACitaAhorita, citaProgramada, irACitaAhorita.isAfter(citaProgramada));
        LOGGER.info("Justo ahora está antes de la Cita Programada? : Hora Actual({}) < Cita({}) = {}",
                irACitaAhorita, citaProgramada, irACitaAhorita.isBefore(citaProgramada));

    }

    public void compareDateAndTime() {
        LocalDateTime citaProgramada = LocalDateTime.of(2021, 12, 5, 18, 30, 0);
        LocalDateTime irACita = LocalDateTime.now();

        LOGGER.info("Justo ahora está después de la Cita Programada? : Hora Actual({}) > Cita({}) = {}",
                irACita, citaProgramada, irACita.isAfter(citaProgramada));
        LOGGER.info("Justo ahora está antes de la Cita Programada? : Hora Actual({}) < Cita({}) = {}",
                irACita, citaProgramada, irACita.isBefore(citaProgramada));
    }

    public void medirTiempo() throws InterruptedException {

        // Using Java 7
        /*long inicio = System.currentTimeMillis();
        Thread.sleep(1000);
        long fin = System.currentTimeMillis();

        LOGGER.info("Tiempo transcurrido entre Inicio y Fin : {}", fin - inicio);*/


        // Using Java 8
        Instant inicio = Instant.now();
        Thread.sleep(1000);
        Instant fin = Instant.now();
        LOGGER.info("Tiempo transcurrido entre Inicio y Fin : {}", Duration.between(inicio, fin).toMillis());
    }

    public void periodoEntreFechas(int version) {

        // Using Java 7
        if(version == 7) {
            Calendar fechaNacimiento = Calendar.getInstance();
            Calendar fechaActual = Calendar.getInstance();

            fechaNacimiento.set(1993, 6, 27);

            int years = 0;
            while(fechaNacimiento.before(fechaActual)) {
                fechaNacimiento.add(Calendar.YEAR, 1);
                if (fechaNacimiento.before(fechaActual)) {
                    years++;
                }
            }
            LOGGER.info("Años de Edad : {}", years);
        }

        // Using Java 8
        else if (version == 8) {
            LocalDate fechaNacimiento = LocalDate.of(1993, 6, 27);
            LocalDate fechaActual = LocalDate.now();

            Period periodo = Period.between(fechaNacimiento, fechaActual);
            LOGGER.info("Han transcurrido {} años, con {} meses y {} días.",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
        }
    }

    public void convertirFecha(int version) {

        String fecha = "27/06/1993";

        // Using Java 7
        if (version == 7) {
            try {
                DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaConvertida = formateador.parse(fecha);
                LOGGER.info("Fecha Convertida : {}", fechaConvertida);

                Date fechaActual = Calendar.getInstance().getTime();
                formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
                String cadena = formateador.format(fechaActual);
                LOGGER.info("Fecha Cadena : {}", cadena);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Using Java 8
        else if (version == 8) {
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaLocal = LocalDate.parse(fecha, formateador);

            LOGGER.info("Fecha Local : {}", fechaLocal);
            LOGGER.info("Fecha Local Formateada: {}", formateador.format(fechaLocal));
        }
    }

}
