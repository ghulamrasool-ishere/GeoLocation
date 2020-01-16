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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GAMA
 */
public class MannualLocationController implements Initializable {
    String lat,lon,latlon;
    ArrayList<String> list1=new ArrayList<String>();
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button setAsHome;
    @FXML
    private Button setAsWork;
    @FXML
    private Button setAsCafe;
    @FXML
    private Button SetLiveLocationAsHome;
    @FXML
    private Button SetLiveLocationAsWork;
    @FXML
    private Button SetLiveLocationAsCafe;
    @FXML
    private Button Done;
    @FXML
    private Label user_name;

    /**
     * Initializes the controller class.
     */
    /**
     * Array list (Observe able type) to store all the provided locations in database....
     */
    ObservableList<String> list = FXCollections.observableArrayList();
    /**
     * this method will automatically run when this frame is loaded 
     * it will load all the stored location in the combo box ....
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt=connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM locations");
            while (result.next()){
            list.add(result.getString(2));
            }
        
        }
        catch (Exception e){
            System.out.println(e);
        }
        combo.setItems(list);
        // TODO
    }    
    /**
     * this will load when setAsHome button is clicked
     * it will get location from the combo box and set it as Home location
     * @param event
     * @throws SQLException
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException 
     */
    @FXML
    private void setAsHome(MouseEvent event) throws SQLException, MalformedURLException, IOException, UnknownHostException, InterruptedException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
        Statement stmt=connection.createStatement();
        ResultSet result1 = stmt.executeQuery("SELECT * FROM locations");
        while (result1.next()){
            if (combo.getValue().equals(result1.getString(2))){
                int id = 0;
                latlon =result1.getString(3);
                int index = latlon.indexOf(",");
                lat = latlon.substring(0,index);
                lon = latlon.substring(index+1);
                ResultSet result = stmt.executeQuery("SELECT * FROM users");
                while (result.next()){
                    if (result.getString(3).equals(user_name.getText())){
                        id = result.getInt(1);
                        break;
                    }   
            }
            stmt.executeUpdate("UPDATE users SET home = '"+latlon+"' WHERE id =" +id);
                getcordinates g = new getcordinates(lat, lon, false);
            }
        }
    }
    /**
     * this will load when setAsWork button is clicked
     * it will get location from the combo box and set it as Work location
     * @param event
     * @throws SQLException
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException 
     */
    @FXML
    private void setAsWork(MouseEvent event) throws SQLException, MalformedURLException, IOException, UnknownHostException, InterruptedException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
        Statement stmt=connection.createStatement();
        ResultSet result1 = stmt.executeQuery("SELECT * FROM locations");
        while (result1.next()){
            if (combo.getValue().equals(result1.getString(2))){
                int id = 0;
                latlon =result1.getString(3);
                int index = latlon.indexOf(",");
                lat = latlon.substring(0,index);
                lon = latlon.substring(index+1);
                ResultSet result = stmt.executeQuery("SELECT * FROM users");
                while (result.next()){
                    if (result.getString(3).equals(user_name.getText())){
                        id = result.getInt(1);
                        break;
                    }   
            }
            stmt.executeUpdate("UPDATE users SET work = '"+latlon+"' WHERE id =" +id);
                getcordinates g = new getcordinates(lat, lon, false);
            }
        }
    }
    /**
     * this will load when setAsCafe button is clicked
     * it will get location from the combo box and set it as Cafe location
     * @param event
     * @throws SQLException
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException 
     */
    @FXML
    private void setAsCafe(MouseEvent event) throws SQLException, MalformedURLException, IOException, UnknownHostException, InterruptedException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
        Statement stmt=connection.createStatement();
        ResultSet result1 = stmt.executeQuery("SELECT * FROM locations");
        while (result1.next()){
            if (combo.getValue().equals(result1.getString(2))){
                int id = 0;
                latlon =result1.getString(3);
                int index = latlon.indexOf(",");
                lat = latlon.substring(0,index);
                lon = latlon.substring(index+1);
                ResultSet result = stmt.executeQuery("SELECT * FROM users");
                while (result.next()){
                    if (result.getString(3).equals(user_name.getText())){
                        id = result.getInt(1);
                        break;
                    }   
            }
            stmt.executeUpdate("UPDATE users SET cafe = '"+latlon+"' WHERE id =" +id);
                getcordinates g = new getcordinates(lat, lon, false);
            }
        }
    }
    /**
     * This method will load when button SetLiveLocationAsHome is clicked\
     * it will set Live Location as Home in user database for provided username
     * @param event
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws SQLException
     * @throws InterruptedException 
     */
    @FXML
    private void SetLiveLocationAsHome(MouseEvent event) throws MalformedURLException, IOException, UnknownHostException, SQLException, InterruptedException {
        getcordinates g = new getcordinates("livehome", user_name.getText(), true);
    }
    /**
     * This method will load when button SetLiveLocationAsWork is clicked\
     * it will set Live Location as Work in user database for provided username
     * @param event
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws SQLException
     * @throws InterruptedException 
     */
    @FXML
    private void SetLiveLocationAsWork(MouseEvent event) throws MalformedURLException, IOException, UnknownHostException, SQLException, InterruptedException{
        getcordinates g = new getcordinates("livework", user_name.getText(), true);
    }
    /**
     * This method will load when button SetLiveLocationAsCafe is clicked
     * it will set Live Location as Cafe in user database for provided username
     * @param event
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws SQLException
     * @throws InterruptedException 
     */
    @FXML
    private void SetLiveLocationAsCafe(MouseEvent event) throws MalformedURLException, IOException, UnknownHostException, SQLException, InterruptedException{
        getcordinates g = new getcordinates("livecafe", user_name.getText(), true);
    }
    /**
     * this method will load when done button is clicked 
     * this method will redirect to the main menu scene ....
     * @param event
     * @throws IOException 
     */
    @FXML
    private void done(MouseEvent event) throws IOException {
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
