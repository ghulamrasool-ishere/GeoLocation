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
import java.sql.SQLException;
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

public class MainMenuController implements Initializable {
    
    @FXML // fx:id="edit_see_info"
    private Button edit_see_info; // Value injected by FXMLLoader

    @FXML // fx:id="saved_locations"
    private Button saved_locations; // Value injected by FXMLLoader

    @FXML // fx:id="live_locations"
    private Button live_locations; // Value injected by FXMLLoader

    @FXML // fx:id="delete_account"
    private Button delete_account; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader

    @FXML // fx:id="user_name"
    private Label user_name; // Value injected by FXMLLoader
    /**
     * this method will called when show details button is pressed
     * if this method is called it will redirect you to the show details frame 
     * @param event
     * @throws IOException
     * @throws SQLException 
     * 
     */
    @FXML
    void showdetails(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("Details.fxml"));
        Parent r = loader.load();
        Scene scene = new Scene(r);
        DetailsController add = loader.getController();
        add.getusername(user_name.getText());
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setResizable(false);
        s.centerOnScreen();
        s.setScene(scene);
    }
    /**
     * this method will be called when live location button is pressed 
     * if this method is called it will load the live location....
     * @param event
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnknownHostException
     * @throws SQLException
     * @throws InterruptedException 
     * 
     */
    @FXML
    void livelocation(MouseEvent event) throws MalformedURLException, IOException, UnknownHostException, SQLException, InterruptedException {
        getcordinates g = new getcordinates("", "", true);
    }
    /**
     * this method will be called when logout button is pressed
     * if this method is called it will redirect you to the login frame and log you out from the application
     * @param event
     * @throws IOException
     * 
     */
    @FXML
    void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent r = loader.load();
        Scene scene = new Scene(r);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setResizable(false);
        s.centerOnScreen();
        s.setScene(scene);
    }
    /**
     * If this method is called it will redirect you to the Saved Locations Frame
     * @param event
     * @throws IOException 
     */
    @FXML
    void savedLocations(MouseEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("SavedLocations.fxml"));
            Parent r = loader.load();
            Scene scene = new Scene(r);
            SavedLocationsController add = loader.getController();
            add.getusername(user_name.getText());
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setResizable(false);
            s.centerOnScreen();
            s.setScene(scene);
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * this method will get username from previous scene and set that to the username label in the current scene
     * @param s 
     */
    public void getusername (String s){
        user_name.setText(s);
    }
}
