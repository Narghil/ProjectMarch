package hu.gaborpernyei;

import java.util.*;

public class Olympics {
    private final static Map<Integer, String> GAMES = new HashMap();

    static {
        GAMES.put(1964, "Japan");
        GAMES.put(1968, "Mexico");
        GAMES.put(1972, "Germany");
        GAMES.put(1976, "Canada");
        GAMES.put(1980, "Russia");
        GAMES.put(1984, "USA");
        GAMES.put(1988, "South Korea");
        GAMES.put(1992, "Spain");
        GAMES.put(1996, "USA");
        GAMES.put(2000, "Australia");
        GAMES.put(2004, "Greece");
        GAMES.put(2008, "China");
        GAMES.put(2012, "UK");
        GAMES.put(2016, "Brazil");
        GAMES.put(2021, "Japan");
        GAMES.put(2024, "France");
        GAMES.put(2028, "USA");
    }

    enum Continents{
        EURÓPA("Germany","Spain","Greece","UK","France"),
        ÁZSIA("Russia","South Korea","China","Japan"),
        AMERIKA("Mexico","USA","Brazil","Canada"),
        AUSZTRÁLIA("Australia");

        String[] countries;
        Continents( String... cou){
            this.countries = cou;
        }
    }

    //A megadott ország kontinense
    public static String getContinent( String country ){
        for (Continents co:Continents.values() ) {
            if( Arrays.asList(co.countries).contains(country) ){
                return co.name();
            }
        }
        return "n.a.";
    }

    //Olimpiák száma a megadott kontinenseken
    public static void numOfOlympiadsInContinents( Continents ... continents ){
        for( Continents co:continents) {
            System.out.print("Megtartott olimpiák száma " + co.name() + " kontinensén:");
            System.out.println(GAMES.entrySet().stream().filter(e -> Arrays.asList(co.countries).contains(e.getValue())).count());
        }
    }

    //Olimpiák száma a megadott országokban
    public static void numOfOlympiadsInCountries( String ... countries ){
        for( String country:countries){
            System.out.print("Megtartott olimpiák száma ebben az országban:" + country+":");
            System.out.println(GAMES.entrySet().stream().filter(e -> e.getValue().equals(country) ).count());
        }
    }

    //Olimpiák helyszíne a megadott években
    public static void olympiadsInYears( Integer ... years ){
        for (Integer year:years ) {
            System.out.print("Az Olimpia helyszíne a "+year+". évben:");
            GAMES.entrySet().stream().filter(e -> e.getKey().equals(year) ).forEach( e -> System.out.println(e.getValue()) ) ;
        }
    }

    //Olimpiát rendező országok, a kontinensükkel együtt
    public static void countriesAndContinents(){
        System.out.println("Rendező országok és kontinensük:");
        Arrays.stream(GAMES.values().toArray())
                .distinct().forEach( e-> System.out.println( e.toString() + ":" + getContinent(e.toString() ) ) );
    }

    public static void main(String[] args) {
        numOfOlympiadsInContinents( Continents.EURÓPA, Continents.ÁZSIA, Continents.AMERIKA );
        numOfOlympiadsInCountries( "USA" );
        olympiadsInYears( 1964, 1984, 2004, 2024 );
        countriesAndContinents();
    }
}


