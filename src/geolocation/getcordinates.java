/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocation;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author GAMA
 * to get coordinates
 * to generate URL for browser in java script form
 * to load the generated URL in JxBrowser API
 * 
 */
public class getcordinates {
    String lat,lon,status,user_name;
    Boolean live;
/**
 *   @param lat
     * @param live
     * @param lon
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws java.sql.SQLException
     * @throws java.lang.InterruptedException
     * 
 * This method has some conditions 
 * 
 * 1) If latitude and longitude which are passed are empty and boolean check is true then it will find the live coordinates and then
 *    generate the URL for browser using that coordinates and in the end Browser will load.
 * 2) If latitude and Longitude have some value and Boolean check false then only URL file will be generated and Browser will load.
 * 3) If latitude and Longitude have some specified values e.g (
 *      livehome, username
 *      livecafe, username
 *      livework, username
 * ) then it will work as condition 1 (as described) and also save that coordinates to the database...
 */
    public getcordinates(String lat, String lon, boolean live) throws UnknownHostException, MalformedURLException, IOException, SQLException, InterruptedException {
        this.lat = lat;
        this.lon = lon;
        this.live = live;
        this.status = lat;
        this.user_name = lon;
        if (this.live==true){
            URL whatismyip = new URL("http://checkip.amazonaws.com");//Requesting public IP from Amazon...
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String i = in.readLine(); //you get the IP as a String
            System.out.println(i);
            URL url;
            try {
                // get URL content
                String a="http://free.ipwhois.io/json/"+i;// Requesting ipwhois for our coordinates against public ip provided
                url = new URL(a);
                URLConnection conn = url.openConnection();
                // open the stream and put it into BufferedReader
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                inputLine = br.readLine();
                System.out.println(inputLine);
                /**
                 * generating Longitude and Latitude Strings using string methods
                 */
                int index1 = inputLine.indexOf("longitude");
                int index2 = inputLine.indexOf(",", index1+12);
                int index3 = inputLine.indexOf("latitude");
                int index4 = inputLine.indexOf(",", index3+11);
                lon = inputLine.substring(index1+12,index2-1);
                lat = inputLine.substring(index3+11,index4-1);
                if (status.equals("livehome")||status.equals("livework")||status.equals("livecafe")){
                    /**
                     * Connecting to the database...
                     */
                    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "geolocations?zeroDateTimeBehavior=convertToNull","root","");
                    Statement stmt=connection.createStatement();
                    ResultSet result1 = stmt.executeQuery("SELECT * FROM users");
                    int id = 0;
                    while (result1.next()){//Getting id against provided username from database
                        if (result1.getString(3).equals(user_name)){
                        id = result1.getInt(1);
                        break;
                        }   
                    }
                    if (status.equals("livehome")){
                        stmt.executeUpdate("UPDATE users SET home = '"+lat+","+lon+"' WHERE id =" +id);//Updating Database...
                    }
                    else if (status.equals("livework")){
                        stmt.executeUpdate("UPDATE users SET work = '"+lat+","+lon+"' WHERE id =" +id);//Updating Database...
                    }
                    else {
                        stmt.executeUpdate("UPDATE users SET cafe = '"+lat+","+lon+"' WHERE id =" +id);//Updating Database...
                    }
                
                br.close();
            } 
            
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
    }
        
        FileOutputStream fout;
        try{    
            fout =new FileOutputStream("E:///map.html");    
            String s="<!DOCTYPE html><html><head><meta name=\"viewport\" content="
                     + "\"initial-scale=1.0, user-scalable=no\"/><style type=\"text/css\">"
                     + "html { height: 100% }body { height: 100%; margin: 0; padding: 0 }"
                     + "#map-canvas { height: 100% }</style><script type=\"text/javascript"
                     + "\"src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyD8ar3yq08y"
                     + "rZ8CpLikAoLearFvRIL7RoI\"></script><script type=\"text/javascript\">var"
                     + " map;function initialize() {var mapOptions = {center: new google.maps."
                     + "LatLng("+lat+","+lon+"),zoom: 17};map = new google.maps."
                     + "Map(document.getElementById(\"map-canvas\"), mapOptions);}google.maps."
                     + "event.addDomListener(window, 'load', initialize);</script></head><body>"
                     + "<div id=\"map-canvas\"></div></body></html>"; 
            byte b[]=s.getBytes();//converting string into byte array
            fout.write(b);
            fout.close(); 
            System.out.println("success...");
        }
        catch(Exception e){
            System.out.println(e);
        }
        /**
         * Checking validity of JxBrowser License...
         */
        Engine engine = Engine.newInstance(
        EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                .licenseKey("6P830J66YADUJUDKAG4FW4FM9JTOWW5HM08ZTX8ZVKAMOJILSCSAEFQDGD163BM86C3S")
                .build());
        Browser browser = engine.newBrowser();
        BrowserView view = BrowserView.newInstance(browser);//Creating Browser View 
        JFrame frame = new JFrame("Google Maps");//Ceating Frame
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        browser.navigation().loadUrl("E:///map.html");  
        TimeUnit.SECONDS.sleep(10);
        Helper.delete();
    }     
}
