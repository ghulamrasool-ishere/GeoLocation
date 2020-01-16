/**
 * Sample Skeleton for 'Welcome.fxml' Controller Class
 */

package geolocation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author GAMA
 * In this class You can sign up
 * you can go to the Login Page
 */
public class WelcomeController {

    @FXML // fx:id="signup_username"
    private TextField signup_username; // Value injected by FXMLLoader

    @FXML // fx:id="signup_password"
    private PasswordField signup_password; // Value injected by FXMLLoader

    @FXML // fx:id="signup_cpassword"
    private PasswordField signup_cpassword; // Value injected by FXMLLoader

    @FXML // fx:id="signup_warninglabel"
    private Label signup_warninglabel; // Value injected by FXMLLoader

    @FXML // fx:id="signup_userwarning"
    private Label signup_userwarning; // Value injected by FXMLLoader
    
    /**
     * This method is called when Login Button is hit
     * You will be redirected to the Login Page
     * @param event
     * @throws IOException 
     */
    @FXML
    void signin(MouseEvent event) throws IOException {
        Parent  r=FXMLLoader.load(getClass().getResource("Login.fxml"));
        Node n=(Node) event.getSource();
        Stage s = (Stage) n.getScene().getWindow();
        Scene sc= new Scene(r);
        s.centerOnScreen();
        s.setScene(sc);
    }
    /**
     * This method will be called when you hit Sign up Button
     * Here are few condition 
     * 1) if user_name field is empty then it will warn you
     * 2) if password fields are empty then it will warn you
     * 3) If password and confirm password field are not same then it will warn you..
     * 4) If username is already present in database then it will warn you...
     * @param event
     * @throws IOException 
     */
    @FXML
    void signup(MouseEvent event) throws IOException {
        signup_warninglabel.setText("");
        
        signup_userwarning.setText("");
        if (signup_username.getText().equals("")){
            signup_userwarning.setText("Field cannot be empty !!!");
        }
        else if (signup_password.getText().equals(signup_cpassword.getText())== false){
            signup_warninglabel.setText("Password didn't matched !!!");
        }
        else if (signup_password.getText().equals("") || signup_cpassword.getText().equals("")){
            signup_warninglabel.setText("Password field cannot be empty !!!");
        }
        else{
            try{
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("INSERT INTO users (user_name,name,password,age,occupation,email,home,work,cafe) "
                        + "VALUES ('"+signup_username.getText()+"', '', '"+signup_password.getText()+"',0,'',"
                        + "'','','','')");
                Parent  r=FXMLLoader.load(getClass().getResource("Login.fxml"));
                Node n=(Node) event.getSource();
                Stage s = (Stage) n.getScene().getWindow();
                Scene sc= new Scene(r);
                s.setResizable(false);
                s.centerOnScreen();
                s.setScene(sc);
                connection.close();
            }//try end
            catch(SQLException e ){
                signup_userwarning.setText("User Already Exist !!! ");
            }//catch end
        }
    }

}
