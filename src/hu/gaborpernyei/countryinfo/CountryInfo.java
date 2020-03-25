package hu.gaborpernyei.countryinfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountryInfo {

    public static void main(String[] args) {
        AllCountries countries = new AllCountries();

        countries.loadCountries();
    }

}

//Minden ország osztálya
class AllCountries{
    List<Country> allCountries = new ArrayList<Country>();

    //Országok beolvasása
    public boolean loadCountries(){
        BufferedReader br = null;
        String oneLine;
        String[] rawValues;
        Country aCountry;

        //File beolvasása
        File inputFile = new File("countries.csv");
        try {
            br = new BufferedReader( new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            //A fejléc sor beolvasása és eldobása
            br.readLine();
            //egy sor beolvasása, amíg vannak sorok.
            while ((oneLine = br.readLine()) != null) {
                rawValues = oneLine.split(",");
                aCountry = new Country( rawValues[0], rawValues[1], rawValues[2], rawValues[3]);
                allCountries.add(aCountry);
            }
            br.close();
        } catch (IOException e ){
            e.printStackTrace();
            return false;
        }
        System.out.println("Count of loaded countries:" + allCountries.size());

        return true;
    }


}

//Egy ország osztálya
class Country{
    private String country;
    private String capital;
    private String population; //Csak megjeleníteni fogjuk
    private String pathToJPG;

    public Country(){}

    public Country(String country, String capital, String population, String pathToJPG) {
        this.country = country;
        this.capital = capital;
        this.population = population;
        this.pathToJPG = pathToJPG;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setPathToJPG(String pathToJPG) {
        this.pathToJPG = pathToJPG;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }

    public String getPathToJPG() {
        return pathToJPG;
    }
}
