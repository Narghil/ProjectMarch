package hu.gaborpernyei.countryinfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class CountryInfo extends Application {
    private AllCountries countries = new AllCountries();
    private ComboBox cbCountryNames;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        countries.loadCountries();

        cbCountryNames = new ComboBox();
        cbCountryNames.getItems().addAll(countries.getCountryNames());
        cbCountryNames.getSelectionModel().select(0);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Region spacer = new Region();
        HBox topPanel = new HBox(10, cbCountryNames);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setPadding(new Insets(10));
        HBox.setHgrow(spacer, Priority.ALWAYS);

        BorderPane bp = new BorderPane();
        bp.setTop(topPanel);

        Scene scene = new Scene(bp, 800, 600);
        stage.setTitle("Countries");
        stage.setScene(scene);
        stage.show();
    }
}

//Minden ország osztálya
class AllCountries{
    List<Country> allCountries = new ArrayList<Country>();

    //Országnevek visszaadása tömbként
    public String[] getCountryNames(){
        String[] names;
        Integer i = 0;

        names = new String[allCountries.size()];
        for (Country c:allCountries) {
            names[i] = c.getCountry();
            i ++;
        }

        return names;
    }

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
