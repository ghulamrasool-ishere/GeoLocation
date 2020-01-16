/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GAMA
 */
public class SavedLocationsController implements Initializable {
    
    String lat, lon, latlon;
    
    @FXML
    private Button home;
    @FXML
    private Button work;
    @FXML
    private Button cafe;
    @FXML
    private Button MannualLocation;
    @FXML
    private Button back;
    @FXML
    private Label warning;
    @FXML
    private Label user_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * this method will be called when home button is pressed 
     * it will get the saved home location from user database and load it in browser..
     * if there is no location previously saved it will warn 
     * @param event
     * @throws SQLException
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException 
     */
    @FXML
    private void home(MouseEvent event) throws SQLException, MalformedURLException, IOException, UnknownHostException, InterruptedException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
        Statement stmt=connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        while (result.next()){
            if (result.getString(3).equals(user_name.getText())){
                latlon = result.getString(8);
                if (latlon.equals("")!=true){
                int index = latlon.indexOf(",");
                lat = latlon.substring(0,index);
                lon = latlon.substring(index+1);
                getcordinates g = new getcordinates(lat, lon, false);
                }
                else {
                    warning.setText("No Location Found !!!");
                }
                
                break;
            }
        } 
    }
    /**
     * this method will be called when work button is pressed 
     * it will get the saved work location from user database and load it in browser..
     * if there is no location previously saved it will warn 
     * @param event
     * @throws SQLException
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException 
     */
    @FXML
    private void work(MouseEvent event) throws MalformedURLException, IOException, SQLException, UnknownHostException, InterruptedException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
        Statement stmt=connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        while (result.next()){
            if (result.getString(3).equals(user_name.getText())){
                latlon = result.getString(9);
                if (latlon.equals("")!=true){
                int index = latlon.indexOf(",");
                lat = latlon.substring(0,index);
                lon = latlon.substring(index+1);
                getcordinates g = new getcordinates(lat, lon, false);
                }
                else {
                    warning.setText("No Location Found !!!");
                }
                
                break;
            }
        }
    }
    /**
     * this method will be called when cafe button is pressed 
     * it will get the saved cafe location from user database and load it in browser..
     * if there is no location previously saved it will warn 
     * @param event
     * @throws SQLException
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException 
     */
    @FXML
    private void cafe(MouseEvent event) throws SQLException, MalformedURLException, IOException, InterruptedException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
        Statement stmt=connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        while (result.next()){
            if (result.getString(3).equals(user_name.getText())){
                latlon = result.getString(10);
                if (latlon.equals("")!=true){
                int index = latlon.indexOf(",");
                lat = latlon.substring(0,index);
                lon = latlon.substring(index+1);
                getcordinates g = new getcordinates(lat, lon, false);
                }
                else {
                    warning.setText("No Location Found !!!");
                }
                
                break;
            }
        }
    }
    /**
     * this method is called when manual location location button is pressed 
     * it will redirect to the manual location scene...
     * @param event
     * @throws IOException 
     */
    @FXML
    private void mannualLocation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("MannualLocation.fxml"));
        Parent r = loader.load();
        Scene scene = new Scene(r);
        MannualLocationController add = loader.getController();
        add.getusername(user_name.getText());
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setResizable(false);
        s.centerOnScreen();
        s.setScene(scene);
    }
    /**
     * this method is called when go back button is pressed 
     * it will redirect to the main menu scene .... 
     * @param event
     * @throws IOException 
     */
    @FXML
    void goback(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("MainMenu.fxml"));
            Parent r = loader.load();
            Scene scene = new Scene(r);
            MainMenuController add = loader.getController();
            add.getusername(user_name.getText());
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setResizable(false);
            s.centerOnScreen();
            s.setScene(scene);
    }
    /**
     * this method will get username from previous scene and set that to the username label in the current scene
     * @param s 
     */
    public void getusername (String s){
        user_name.setText(s);
    }
}
