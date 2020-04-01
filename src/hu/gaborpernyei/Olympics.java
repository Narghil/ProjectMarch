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
        AMERIKA("Mexico","USA","Brazil"),
        AUSZTRÁLIA("Australia");

        String[] countries;
        Continents( String... cou){
            this.countries = cou;
        }
    }

    public static String getContinent( String country ){
        for (Continents co:Continents.values() ) {
            if( Arrays.asList(co.countries).contains(country) ){
                return co.name();
            }
        }
        return "n.a.";
    }

    public static void main(String[] args) {
        System.out.print("Olimpiák az USA-ban:");
        System.out.println(GAMES.entrySet().stream().filter(e -> e.getValue().equals("USA") ).count());

        System.out.print("Olimpiák az Amerikai kontonensen:");
        System.out.println(GAMES.entrySet().stream().filter(e -> Arrays.asList(Continents.AMERIKA.countries).contains(e.getValue()) ).count() );

        System.out.print("Olimpia 1984-ben:");
        GAMES.entrySet().stream().filter(e -> e.getKey() == 1984 ).forEach( e -> System.out.println(e.getValue()));

        System.out.println("Rendező országok és kontinensük:");
        Arrays.stream(GAMES.values().toArray())
                .distinct().forEach( e-> System.out.println( e.toString() + ":" + getContinent(e.toString() ) ) );
    }
}


