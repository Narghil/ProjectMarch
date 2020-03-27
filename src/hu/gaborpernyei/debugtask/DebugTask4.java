package hu.gaborpernyei.debugtask;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UGYFEL BEJELENTES:
 *  Nem lehet szulinapi partit letrehozni, mert elszall!
 *
 * UGYFEL UJ IGENY:
 *  A javitason kivul azt meg lehetne csinalni, hogy azoknak a gyerekeknek akik nem kerulnek bele a partiba is javasoljunk valamit?
 */
public class DebugTask4 {

    public static void main(String[] args) {
        Child ch1 = new Child("Parker", SweetOrFruit.Melon, GameOrToy.PlaticSoldier);
        Child ch2 = new Child("Cynthia", SweetOrFruit.JollyRancher, GameOrToy.Sandbox);
        Child ch3 = new Child("Samuel");
        ch3.setFavoriteGame(GameOrToy.Bicycle);

        Child ch4 = new Child("Zack");
        ch4.setFavoriteGame(GameOrToy.ComputerGames);
        ch4.setFavoriteSweets(SweetOrFruit.Muffin);

        Child ch5 = new Child("Alicia", SweetOrFruit.Icecream, GameOrToy.Barbie);
        Child ch6 = new Child("Jessica", SweetOrFruit.Melon, GameOrToy.Sandbox);
        Child ch7 = new Child("Arda", SweetOrFruit.Mango, GameOrToy.GameBoy);
        Child ch8 = new Child("Baris");

        ch8.setFavoriteGame(GameOrToy.Ball);

        arrangeParty(Arrays.asList(ch8, ch7, ch2, ch4), Event.OnlineGaming);
        arrangeParty(Arrays.asList(ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8), Event.BallPark);
        arrangeParty(Arrays.asList(ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8), Event.BirthDayParty);
    }

    static void arrangeParty(List<Child> children, Event event) {
        switch (event) {
            case BallPark: {
                Object[] attendance = children.stream().filter(ch -> ch.getFavoriteGame().equals(GameOrToy.Bicycle) ||
                        ch.getFavoriteGame().equals(GameOrToy.Ball) ||
                        ch.getFavoriteGame().equals(GameOrToy.CarRace) ||
                        ch.getFavoriteGame().equals(GameOrToy.Sandbox)).toArray();

                if (attendance.length > 1) {
                    System.out.println("These guys can go out to Ball Park: " + Arrays.asList(attendance));
                }

                break;
            }

            case OnlineGaming: {
                Object[] attendance = children.stream().filter(ch -> ch.getFavoriteGame().equals(GameOrToy.GameBoy) ||
                        ch.getFavoriteGame().equals(GameOrToy.ComputerGames)).toArray();
                if (attendance.length > 1) {
                    System.out.println("These guys can have online gaming: " + Arrays.asList(attendance));
                }
                break;
            }

            case BirthDayParty: {
                List<Child> attendance = children.stream().filter(ch -> ch.getFavoriteSweets().equals(SweetOrFruit.Icecream) ||
                        ch.getFavoriteSweets().equals(SweetOrFruit.JollyRancher) ||
                        ch.getFavoriteSweets().equals(SweetOrFruit.Muffin) ||
                        ch.getFavoriteSweets().equals(SweetOrFruit.VanillaShake)).collect(Collectors.toList());
                if (attendance.size() > 1) {
                    System.out.println("These guys can have a birthday party: " + Arrays.asList(attendance));
                }

                //PG: Ők nincsenek a partiban
                List<Child> notAttendance = children;
                notAttendance.removeAll(attendance);

                // PG: Ez lenne a kért fejlesztés...
                if (notAttendance.size() > 1) {
                    System.out.println("These guys can not have a birthday party: " + notAttendance);
                    System.out.println("Javaslatok:");
                    arrangeParty(notAttendance, Event.OnlineGaming);
                    arrangeParty(notAttendance, Event.BallPark);
                }

                break;
            }
        }
    }
}

class Child {

    private SweetOrFruit favoriteSweets;
    private GameOrToy favoriteGame;
    private String name;

    public Child(String n, SweetOrFruit f, GameOrToy g) {
        name = n;
        favoriteSweets = f;
        favoriteGame = g;
    }

    public Child(String name) {
        this.name = name;
        //PG: Javítás
        this.favoriteGame = GameOrToy.None;
        this.favoriteSweets = SweetOrFruit.None;
    }

    public String getName() {
        return name;
    }

    public SweetOrFruit getFavoriteSweets() {
        return favoriteSweets;
    }

    public void setFavoriteSweets(SweetOrFruit favoriteSweets) {
        this.favoriteSweets = favoriteSweets;
    }

    public GameOrToy getFavoriteGame() {
        return favoriteGame;
    }

    public void setFavoriteGame(GameOrToy favoriteGame) {
        this.favoriteGame = favoriteGame;
    }

    @Override
    public String toString() {
        return name;
    }
}

enum SweetOrFruit {
    None, Melon, Mango, Kiwi, Raspberry, Icecream, Muffin, JollyRancher, VanillaShake;
}

enum GameOrToy {
    None, PlaticSoldier, Ball, Barbie, CarRace, Bicycle, Lego, Puzzle, Sandbox, GameBoy, ComputerGames;
}

enum Event {
    None, BirthDayParty, BallPark, OnlineGaming
}