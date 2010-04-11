package restaurant;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author annaatwood
 */
public class orderedItem {
    private int menuId;
    private String name;
    private String status;
    private int rating;
    private String details;
    private boolean ratingset;

    public orderedItem(int menuid, String dish, String detail){
        menuId = menuid;
        name = dish;
        details = detail;
        status = "ordered";
        rating = 0;
        ratingset = false;
    }
    public void setRating(int rate){
        rating = rate;
        ratingset=true;
    }

    public boolean ratingAlreadySet(){
        return ratingset;
    }
    public void setStatus(String stat){
        status = stat;
    }
    public String toString(){
        return menuId + "\t" + name + "\t" + status;
    }

}
