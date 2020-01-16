/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocation;

import java.io.File;

/**
 *
 * @author GAMA
 */
public class Helper {
    /**
     * This method will delete the dump map.html file which is created every time  when browser load
     * it must be deleted because it will effect next browser in loading.....
     */
    public static void delete (){
        File file = new File("E:///map.html"); 
          
                    if(file.delete()) 
                    { 
                        System.out.println("File deleted successfully"); 
                    } 
                    else
                    { 
                        System.out.println("Failed to delete the file"); 
                    }
    }
}
