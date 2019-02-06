/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Shane
 */
public class HelperFunctions {
    
    public static Date convertLocalDateToDate(LocalDate date){
        return Date.from((date).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static boolean checkNameRequests(HttpServletRequest request){
        String[] nameRequestVariables = {"street", "city", "listing", "style", 
            "type", "bedrooms", "bathrooms", "squarefeet", "ber", "description",
            "garagesize", "gtype", "price", "lotsize"};
        
        for(String s : nameRequestVariables){
            if(request.getParameter(s) == null){
                System.out.println(s + " : " + request.getParameter(s));
                return false;
            }
        }
        return true;
    }
    
    public static void saveImages(String UPLOAD_DIRECTORY, String IMAGE_EXTENSION, 
            String listingNumber,  List<Part> images){
        try{
            for(int i = 0; i < images.size(); i++){
                Part image = images.get(i);
                //String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();

                final String saveDirectory = UPLOAD_DIRECTORY + 
                        File.separator + listingNumber;

                File dir = new File(saveDirectory);
                if (!dir.exists()) 
                    dir.mkdirs();

                Files.copy(image.getInputStream(), Paths.get(saveDirectory 
                        + File.separator + listingNumber + 
                        (i == 0 ? "" : "-" + i) + IMAGE_EXTENSION),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public static void deleteFolder(File folder) {
    File[] files = folder.listFiles();
    if(files!=null) { //some JVMs return null for empty dirs
        for(File f: files) {
            if(f.isDirectory())
                deleteFolder(f);
            else
                f.delete();
        }
    }
    folder.delete();
}
}
