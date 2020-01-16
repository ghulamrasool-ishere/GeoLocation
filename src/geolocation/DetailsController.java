/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocation;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GAMA
 */
public class DetailsController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label age;
    @FXML
    private Label email;
    @FXML
    private Label occupation;
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
     * this method will called when back button is pressed
     * if this method is called it will take back to the main menu 
     * @param event
     * @throws IOException 
     * 
     */
    @FXML
    private void BackMainMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("MainMenu.fxml"));//Loading Main menu
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
     * This method will get all data of the passed username from the database...
     * @param s
     * @throws SQLException 
     */
    public void getusername (String s) throws SQLException{
        user_name.setText(s);  
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");//Getting all data from database against passed username....
        Statement stmt=connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        while (result.next()){
            if (result.getString(3).equals(user_name.getText())){
                name.setText(result.getString(2));
                age.setText(result.getString(5));
                occupation.setText(result.getString(6));
                email.setText(result.getString(7));
                break;
            }
        }
    }  
}
