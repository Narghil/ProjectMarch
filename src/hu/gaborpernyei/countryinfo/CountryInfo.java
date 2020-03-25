package hu.gaborpernyei.countryinfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CountryInfo extends Application {
    private AllCountries countries = new AllCountries();
    private ComboBox cbCountryNames;
    private Image capitalImage;
    private ImageView imageView = new ImageView(capitalImage);
    private Label lbCapitalName = new Label("Name of the capital city");
    private Label lbPopulation = new Label("Population");

    public static void main(String[] args) {
        Application.launch(args);
    }

    private class ComboBoxHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            String name;
            String[] retVals;

            name = cbCountryNames.getSelectionModel().getSelectedItem().toString();
            System.out.println( name );
            retVals = countries.getCountryDatas( name );
            try {
                capitalImage = new Image( new FileInputStream( retVals[0] ) );
                imageView.setImage(capitalImage);
                lbCapitalName.setText(retVals[1]);
                lbPopulation.setText(retVals[2]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() throws Exception {
        countries.loadCountries();

        cbCountryNames = new ComboBox();
        cbCountryNames.getItems().addAll(countries.getCountryNames());
        cbCountryNames.getSelectionModel().select(0);
        ComboBoxHandler cbxHandler = new ComboBoxHandler();
        cbCountryNames.setOnAction(cbxHandler);
        cbxHandler.handle(null);

        //Setting the position of the image
        //imageView.setX(50);
        //imageView.setY(25);
        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Region spacer = new Region();
        HBox topPanel = new HBox(10, cbCountryNames);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setPadding(new Insets(10));
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox centerPanel = new HBox(10, imageView);
        centerPanel.setAlignment(Pos.CENTER);
        centerPanel.setPadding(new Insets(10));
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomPanel = new HBox( 10, lbCapitalName, lbPopulation);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.setPadding(new Insets(10));
        HBox.setHgrow(spacer, Priority.ALWAYS);

        BorderPane bp = new BorderPane();
        bp.setTop(topPanel);
        bp.setCenter(centerPanel);
        bp.setBottom(bottomPanel);

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

    //Adott nevű országhoz tartozó picture elérési útvonala, főváros neve, népesség
    public String[] getCountryDatas( String name){
        String[] retVal = new String[3];
        for (Country c:allCountries) {
            if( c.getCountry() == name ){
                retVal[0] = c.getPathToJPG();
                retVal[1] = c.getCapital();
                retVal[2] = c.getPopulation();
            }
        }
        return retVal;
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
