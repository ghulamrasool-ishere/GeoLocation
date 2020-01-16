/**
 * Sample Skeleton for 'AddNewUserRecord.fxml' Controller Class
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author GAMA
 * This class will take required user data and store it in database
 */
public class AddNewUserRecordController implements Initializable{

    @FXML // fx:id="name"
    private TextField name; // Value injected by FXMLLoader

    @FXML // fx:id="age"
    private TextField age; // Value injected by FXMLLoader

    @FXML // fx:id="occupation"
    private TextField occupation; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TextField email; // Value injected by FXMLLoader

    @FXML // fx:id="name_waring"
    private Label name_waring; // Value injected by FXMLLoader

    @FXML // fx:id="age_warning"
    private Label age_warning; // Value injected by FXMLLoader

    @FXML // fx:id="occupation_warning"
    private Label occupation_warning; // Value injected by FXMLLoader

    @FXML // fx:id="email_warning"
    private Label email_warning; // Value injected by FXMLLoader

    @FXML // fx:id="name_label"
    private Label user_name; // Value injected by FXMLLoader
    
    @FXML
    private Button contine;
    /**
     * this method will called when continue button is pressed....
     * This method will get some important data and store it in database
     * It has following conditions
     * 1) If Email is empty or it doesn't have @ character it will warn
     * 2) If name field is empty it will warn
     * 3) if Age field is empty it will warn
     * 4) If occupation field is empty it will warn
     * 5) If all set then it will save all data to the database and take you to the main menu Screen
     * @param event
     * @throws SQLException
     * @throws IOException 
     
     */
    @FXML
    void mainmenu(MouseEvent event) throws SQLException, IOException {
        char alphabets[] = email.getText().toCharArray();//Getting text from email field and converting it into char array.
        for (char alphabet : alphabets){
            if (alphabet == '@'){// If @ character is found in array then it will break the loop
                email_warning.setText("");
                break;
            }
            email_warning.setText("email is not valid !!!");// If it doesn't find @ character it will warn
            
        }
        if (name.getText().equals("")){
            name_waring.setText("name cannot be empty !!!");
            
        }
        else if (age.getText().equals("")){
            age_warning.setText("age cannot be empty !!!");
            
        }
        else if (occupation.getText().equals("")){
            occupation_warning.setText("occupation cannot be empty !!!");
            
        }
        else if (email.getText().equals("")||email_warning.getText().equals("email is not valid !!!")){
            email_warning.setText("email cannot be empty !!!");
            
        }
        
        else{
            int id = 0;
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");//connecting to the database
            Statement stmt=connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM users");
            while (result.next()){
                if (result.getString(3).equals(user_name.getText())){
                    id = result.getInt(1);
                    break;
                }
            }
            stmt.executeUpdate("UPDATE users SET name = '"+name.getText()+"', age ='"+age.getText()+"'"
                                + ",occupation = '"+occupation.getText()+"', email = '"+ email.getText()+"'"
                                + "WHERE id =" +id);//Updating Record...
            connection.close();
            stmt.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("MainMenu.fxml"));//If updated successfully it will redirect to the main menu,.....
            Parent r = loader.load();
            Scene scene = new Scene(r);
            MainMenuController add = loader.getController();
            add.getusername(user_name.getText());
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setResizable(false);
            s.centerOnScreen();
            s.setScene(scene);
        }   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void getusername (String s){
        user_name.setText(s);
        
    }

}
 