package restaurant;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author annaatwood
 */
public class menuItem {
    private String picture;
    private String title;
    private String description;
    private String type;
    private int rating;
    private int menuId;

    //constructor
    public menuItem(String pic, int menuid, String name, String desc,String typ, int rate){
        picture = pic;
        menuId = menuid;
        title = name;
        description = desc;
        rating = 0;
        type = typ;
    }
    public void setRating(int rate){
        rating = rate;
    }
    public int getRating(){
        return rating;
    }
    public String getPicture(){
        return picture;
    }
    public int getMenuId(){
        return menuId;
    }
    public String getName(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getType(){
        return type;
    }
    public String toString(){
        return title + "\t" + description;
    }
}
