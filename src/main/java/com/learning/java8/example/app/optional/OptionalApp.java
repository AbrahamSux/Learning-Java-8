package com.learning.java8.example.app.optional;

import com.learning.java8.example.app.models.entity.Person;
import com.learning.java8.example.app.models.entity.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class OptionalApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalApp.class);



    // MAIN

    public static void main(String[] args) {
        OptionalApp tester = new OptionalApp();

        // Optional con try-catch de manera tradicional
        LOGGER.info(">>> Usando Optional.empty()... ");
        tester.process(null);


        // Usando OrElse
        LOGGER.info(">>> Usando OrElse... ");
        tester.usingOrElse(null);


        // Usando OrElseThrow
        LOGGER.info(">>> Usando OrElseThrow... ");
        /*tester.usingOrElseThrow(null);*/
        // or
        tester.usingOrElseThrow("Test...");


        // Usando IfPresent
        LOGGER.info(">>> Usando IfPresent... ");
        tester.usingIfPresent(null);
        // or
        tester.usingIfPresent("Element String");


        // Diferencia entre OrElseGet y OrElse
        LOGGER.info(">>> Usando 'OrElseGet' And 'OrElse' Differ... ");
        tester.whenOrElseGetAndOrElseDiffer_thenCorrect();

        LOGGER.info(">>> Usando Optional-Filter... ");
        tester.conditionalReturnWithFilter();

        LOGGER.info(">>> Usando Optional-Map... ");
        tester.conditionalReturnWithMap();

        LOGGER.info(">>> Usando Optional: 'Map' And 'Filter'... ");
        tester.whenMapWorksWithFilter(" password ");

        LOGGER.info(">>> Usando Optional: 'FlatMap'... ");
        tester.whenFlatMapWorks();

        LOGGER.info(">>> Usando Chaining Optionals...");
        tester.whenChaining_thenFirstNonEmptyIsReturned();
        tester.whenChaining_thenFirstNonEmptyIsReturned("empty", "hello");
    }



    // METHODS

    public void process(String value) {

        try {
            Optional optional = Optional.empty();
            optional.get();
        } catch (Exception e) {
            LOGGER.error("Error al obtener el elemento, {}", e.getMessage());
        }
    }

    /**
     * Uso del OrElse.
     */
    public void usingOrElse(String value) {
        Optional<String> optional = Optional.ofNullable(value);
        String element = optional.orElse("Predeterminado");

        LOGGER.info("Elmento : {}", element);
    }

    /**
     * Uso del OrElseThrow.
     */
    public void usingOrElseThrow(String value) {
        Optional<String> optional = Optional.ofNullable(value);
        String element = optional.orElseThrow(NumberFormatException::new);

        LOGGER.info("Cadena : {}", element);
    }

    /**
     * Uso del IfPresent.
     */
    public void usingIfPresent(String value) {
        Optional<String> optional = Optional.ofNullable(value);

        LOGGER.info("Existe Elemento ? : {} : {}", optional.isPresent(), optional.orElse("NULL"));
    }

    /**
     * Diferencia entre orElse y orElseGet()
     */
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        LOGGER.info("> Usando orElseGet :");
        String defaultTextOne = Optional.ofNullable(text).orElseGet(this::obtenerCadena);
        LOGGER.info("Text present : {}", defaultTextOne);


        LOGGER.info("> Usando orElse :");
        String defaultTextTwo = Optional.ofNullable(text).orElse(obtenerCadena());
        LOGGER.info("Text present : {}", defaultTextTwo);

        /**
         * Tenga en cuenta que cuando se usa orElseGet() para recuperar el valor envuelto, el método getMyDefault()
         * ni siquiera se invoca ya que el valor contenido está presente.
         *
         * Sin embargo, cuando se usa orElse(), ya sea que el valor envuelto esté presente o no, se crea el objeto
         * predeterminado. Entonces, en este caso, acabamos de crear un objeto redundante que nunca se usa.
         *
         * En este ejemplo simple, no hay un costo significativo para crear un objeto predeterminado, ya que la JVM
         * sabe cómo manejarlo. Sin embargo, cuando un método como getMyDefault() tiene que realizar una llamada a un
         * servicio web o incluso consultar una base de datos, el costo se vuelve muy obvio.
         */
    }

    public String obtenerCadena() {
        LOGGER.debug("Getting Default Value");
        return "Default Value";
    }


    /**
     * Uso del retorno condicional con Filter.
     */
    public void conditionalReturnWithFilter() {
        Integer year = 2021;
        Optional<Integer> yearOptional = Optional.of(year);


        // Caso 1
        boolean is2021 = yearOptional.filter(y -> y == 2021).isPresent();
        LOGGER.info("Es el año 2021 : {}", is2021);

        boolean is2022 = yearOptional.filter(y -> y == 2022).isPresent();
        LOGGER.info("Es el año 2022 : {}", is2022);


        // Caso 2
        Producto producto = new Producto(1, "Laptop", 1, 12499.99, "Electrónicos", "...");
        Optional<Producto> precio = Optional.of(producto);

        boolean isPrecioJusto = precio.filter( x -> x.getPrecio() >= 5000.00).filter(y -> y.getPrecio() <= 10500.00).isPresent();
        LOGGER.info("Está en mi rango de compra : {}", isPrecioJusto);
    }

    /**
     * Uso del retorno condicional con Map.
     */
    public void conditionalReturnWithMap() {
        List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");

        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional.map(List::size).orElse(0);
        LOGGER.info("Tamaño de la lista de compañias : {}", size);

    }

    /**
     * Uso del Map con Filter.
     */
    public void whenMapWorksWithFilter(String password) {

        // Se recibe un password con espacios en vacíos: " password "
        Optional<String> passOpt = Optional.of(password);


        // No son iguales por los espacios
        boolean correctPassword = passOpt.filter(pass -> pass.equals("password")).isPresent();
        LOGGER.info("La contraseña es correcta : {}", correctPassword);


        // Se quitan los espacios (trim) para que sean iguales
        correctPassword = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
        LOGGER.info("La contraseña es correcta : {}", correctPassword);
    }

    /**
     * Uso de FlatMap.
     */
    public void whenFlatMapWorks() {

        Person person = new Person("John Lennon", 26, "12345");
        Optional<Person> personOptional = Optional.of(person);

        // Opción 1
        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);

        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);

        String nameOne = nameOptional.orElse("");
        LOGGER.info("Nombre : {}", nameOne);


        // Opción 2
        String nameTwo = personOptional.flatMap(Person::getName).orElse("");
        int edad = personOptional.flatMap(Person::getAge).orElse(18);
        LOGGER.info("Nombre '{}' y Edad '{}'", nameTwo, edad);
    }

    /**
     * Uso del Encadenamiento de opcionales.
     */
    public void whenChaining_thenFirstNonEmptyIsReturned() {
        Opcionales o = new Opcionales();

        // Caso 1 : recorre todos los métodos y después los evalúa para buscar el primer Optional no vacío.
        /**
         * Para encadenar varios objetos opcionales y obtener el primero que no esté vacío en Java 8,
         * podemos usar la API Stream :
         */
        Optional<String> found = Stream.of(o.getEmpty(), o.getHello(), o.getBye())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        /**
         * La desventaja de este enfoque es que todos nuestros métodos de obtención siempre se ejecutan,
         * independientemente de dónde aparezca un Opcional no vacío en la secuencia.
         */
        LOGGER.info("Primer cadena no vacía Opción 1: {}", found);


        // Caso 2: va recorriendo los métodos hasta encontrar el primer Optional no vacío, si hay más métodos después ya no los ejecuta.
        /**
         * Si queremos evaluar perezosamente los métodos pasados a Stream.of() , necesitamos usar la referencia
         * del método y la interfaz de Supplier:
         */
        Optional<String> foundTwo = Stream.<Supplier<Optional<String>>>of(o::getEmpty, o::getHello, o::getBye)
                        .map(Supplier::get)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst();

        LOGGER.info("Primer cadena no vacía Opción 2: {}", foundTwo);
    }

    /**
     * Uso del Encadenamiento de opcionales (con paso de parámetros).
     */
    public void whenChaining_thenFirstNonEmptyIsReturned(String valueOne, String valueTwo) {
        Opcionales o = new Opcionales();

        /**
         * En caso de que necesitemos utilizar métodos que tomen argumentos, tenemos que recurrir a expresiones lambda:
         */
        Optional<String> found = Stream.<Supplier<Optional<String>>>of(
                () -> o.createOptional( valueOne ),
                () -> o.createOptional( valueTwo )
        )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        LOGGER.info("Primer cadena no vacía : {}", found);


        /**
         * A menudo, queremos devolver un valor predeterminado en caso de que todos los opcionales encadenados estén
         * vacíos. Podemos hacerlo simplemente agregando una llamada a orElse() o orElseGet() :
         */
        String foundDefault = Stream.<Supplier<Optional<String>>>of(
                () -> o.createOptional("empty"),
                () -> o.createOptional("empty")
        )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(() -> "default");

        LOGGER.info("Obtener valor predeterminado si hay solo vacíos : {}", foundDefault);
    }

}



class Opcionales {

    private static final Logger LOGGER = LoggerFactory.getLogger(Opcionales.class);

    public Optional<String> getEmpty() {
        LOGGER.info("getEmpty...");
        return Optional.empty();
    }

    public Optional<String> getHello() {
        LOGGER.info("getHello...");
        return Optional.of("hello");
    }

    public Optional<String> getBye() {
        LOGGER.info("getBye...");
        return Optional.of("bye");
    }

    public Optional<String> createOptional(String input) {
        if (input == null || "".equals(input) || "empty".equals(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

}