package hu.gaborpernyei;

import java.util.Arrays;
import java.util.Random;
//import java.util.function.Predicate;

public class SortingApples {
    public static void main(String[] args) {
        Apple[] apples = new Apple[20]; //new ArrayList() jobb lenne...
        Random rnd = new Random();
        Color color;
        Integer weight;

        for( int i=0; i<apples.length; i++){
            color = Color.values()[ rnd.nextInt(3)];
            weight = rnd.nextInt(30) + 20;
            apples[i] = new Apple( color, weight );
        }

        System.out.println("Szín szerint:");
        Arrays.asList(apples).stream().filter(e -> e.color == Color.RED).forEach(System.out::println);
        System.out.println("Súly és szín szerint:");
        Arrays.asList(apples).stream().filter(e -> e.weight >25).filter( e -> e.color == Color.YELLOW || e.color == Color.RED ).forEach(System.out::println);
        Arrays.asList(apples).stream().filter( Apple::redOrYellow );
    }

}

enum Color{ YELLOW, GREEN, RED }

class Apple{
    Color color;
    Integer weight;

    public Apple( Color c, Integer i){
        this.color = c;
        this.weight = i;
    }

    public String toString(){
        return "Szín:" + this.color.toString() + ", súly:"+this.weight.toString();
    }
    static public Boolean redOrYellow(Apple a) { return a.color == Color.RED || a.color == Color.YELLOW; }
}

