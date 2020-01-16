/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package geolocation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML // fx:id="signin_username"
    private TextField signin_username; // Value injected by FXMLLoader

    @FXML // fx:id="signin_password"
    private PasswordField signin_password; // Value injected by FXMLLoader

    @FXML // fx:id="signin_warninglabel"
    private Label signin_warninglabel; // Value injected by FXMLLoader

    @FXML // fx:id="login_button"
    private Button login_button; // Value injected by FXMLLoader

    @FXML // fx:id="signup_button"
    private Button signup_button; // Value injected by FXMLLoader

    /**
     * this method is called when sign in button is pressed 
     * This method has following conditions to authenticate the provided username 
     * 1) if username field is empty it will warn 
     * 2) if password field is empty it will again warn you...
     * 3) in third condition it will check provided username in database , if username and password matched, it will give access to the next frame
     * otherwise it will give warning.....
     * @param event
     * @throws SQLException
     * @throws IOException 
     * 
     */
    @FXML
    void signin(MouseEvent event) throws SQLException, IOException {
        if (signin_username.getText().equals("")){
            signin_warninglabel.setText("Username cannot be empty !!!");
        }
        else if (signin_password.getText().equals("")){
            signin_warninglabel.setText("Password cannot be empty !!!");
        }
        else {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt=connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM users");
            while (result.next()){
                /**
                 * checking username and password from database.....
                 */
                if(signin_username.getText().equals(result.getString(3))==true && signin_password.getText().equals(result.getString(4))==true){
                    
                    signin_warninglabel.setText("");
                    /**
                     * if any data is missing in database it will ask to provide that data and redirect you to the add user data frame 
                     */
                    if (result.getString(2).equals("")||result.getString(5).equals("")||result.getString(6).equals("")||result.getString(7).equals("")){
                        FXMLLoader loader = new FXMLLoader ();
                        loader.setLocation(getClass().getResource("AddNewUserRecord.fxml"));
                        Parent r = loader.load();
                        Scene scene = new Scene(r);
                        AddNewUserRecordController add = loader.getController();
                        add.getusername(signin_username.getText());
                        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        s.setResizable(false);
                        s.centerOnScreen();
                        s.setScene(scene);
                    }
                    else {
                        /**
                         * if no data of user is missing it will redirect to the main menu .....
                         */
                        FXMLLoader loader = new FXMLLoader ();
                        loader.setLocation(getClass().getResource("MainMenu.fxml"));
                        Parent r = loader.load();
                        Scene scene = new Scene(r);
                        MainMenuController add = loader.getController();
                        add.getusername(signin_username.getText());
                        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        s.setResizable(false);
                        s.centerOnScreen();
                        s.setScene(scene);
                    }
                    break;
                }
                else {
                    signin_warninglabel.setText("Invalid username or password !!!");
                }
                
            }
            
            
        }             
    }
    /**
     * this method is called when sig up button is pressed
     * if this method is called from the login screen it will redirect you to the sign up screen 
     * @param event
     * @throws IOException 
     *  
     */
    @FXML
    void welcome(MouseEvent event) throws IOException {
        Parent  r=FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Node n=(Node) event.getSource();
        Stage s = (Stage) n.getScene().getWindow();
        Scene sc= new Scene(r);
        s.centerOnScreen();
        s.setResizable(false);
        s.setScene(sc);
    }

}
