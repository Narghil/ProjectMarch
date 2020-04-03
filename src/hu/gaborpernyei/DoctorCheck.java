package hu.gaborpernyei;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DoctorCheck {

    // Consumer interfesz: void accept(T t)
    // Predicate interface: boolean test(T t)

    public static void main(String[] args) {
        boolean isHealthy;

        Doctor dr = new Doctor();
        Predicate<String> virusPredicate = dr::healthCheck;
        isHealthy = virusPredicate.test("Everything is OK.");
        System.out.println("isHealthy: " + isHealthy);
        isHealthy = virusPredicate.test("I am diseased");
        System.out.println("isHealthy: " + isHealthy);

        //---------------------------------------------
        Consumer printLnConsumer = (e)->{System.out.println( e );  System.out.println(" - és valóban meg is tette!");} ;

        //A myProcessor.process függvényének az only27-et feleltetjük meg.
        Processor myProcessor = DoctorCheck::only27;

        //consumeThis(System.out::println, "Ez egy string, amit a consumer \"elfogyaszt\"");
        consumeThis(printLnConsumer, "Ez egy string, amit a consumer \"elfogyaszt\"");

        printLnConsumer.accept(2020);
        System.out.println("myProcessor processing 27: " + myProcessor.process(27));
        System.out.println("myProcessor processing 101: " + myProcessor.process(101));

    }

    static void consumeThis(Consumer c, String s) {
        c.accept(s);
    }

    static Processor.Result only27(Object o) {
        if (o instanceof Integer && ((Integer)(o)).equals(27)) {
            return Processor.Result.SUCCESS;
        }

        return Processor.Result.FAILURE;
    }

}

class Doctor {
    boolean healthCheck(String input) {
        return input.indexOf("disease") == -1;
    }
}

@FunctionalInterface
interface Processor {
    enum Result {
        SUCCESS, FAILURE
    }

    Result process(Object o);

}