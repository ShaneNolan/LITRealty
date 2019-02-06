/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Properties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Shane
 */
public class UserInformation implements Serializable{

    HashMap<String, Integer> searchedURLs;
    final String userID;
    HashSet<Integer> favourites;
    
    public UserInformation(String userID) {
        this.userID = userID;
        searchedURLs = new HashMap<>();
        favourites = new HashSet<>();
    }
    
    public void addURL(String url){
        Integer count = searchedURLs.get(url);
        if(count == null)
            searchedURLs.put(url, 1);
        else
            searchedURLs.put(url, count + 1);
    }
    
    public void addFavourite(Integer id){
        favourites.add(id);
    }
    
    public void removeFavourite(Integer id){
        favourites.remove(id);
    }
    
    public String getUserID(){
        return userID;
    }
    
    public HashMap<String, Integer> getSearchedURLs() {
        return searchedURLs;
    }

    public void setSearchedURLs(HashMap<String, Integer> searchedURLs) {
        this.searchedURLs = searchedURLs;
    }

    public HashSet<Integer> getFavourites() {
        return favourites;
    }

    public void setFavourites(HashSet<Integer> favourites) {
        this.favourites = favourites;
    }
}
