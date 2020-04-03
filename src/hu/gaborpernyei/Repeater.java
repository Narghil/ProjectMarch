package hu.gaborpernyei;
import java.util.function.Consumer;

public class Repeater {

    public static void main(String[] args) {
        Consumer<String> str = (x) -> System.out.print("*") ;
        Consumer<String> str2 = (x) -> { x="!"; System.out.print( x );} ;
        str.andThen(str).andThen(str).andThen(str).andThen(str2).accept(null);
    }
}
