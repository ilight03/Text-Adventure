
/*
Location.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This class represents an Location that can be interacted with by the player.  
*/
public class Location {
    // State of the location object
    String name;
    String description;

    // The arrays could be ArrayLists for ease of use - look them up and learn how
    // you can use them.
    Item item; // to hold all of the items in this location.
    Location n; // to hold all of the locations that you can get to from this location.
    Location e;
    Location s;
    Location w;
    Location lookE;
    Location lookW;
    Mob mobs;
    boolean canMove;

    
    public Location (){

    }
    public Location(String name, String description, Item item) {
        this.name = name;
        this.description = description;
        this.item = item;
        this.canMove = true;
    };

    public void setDesc(String desc){
        this.description = desc;
    }

    public void getNeighbor(String direction, Location name){

    }

    public String getName(){
        return name;
    }


    public void removeMob(){
        this.mobs = null;
    }
    public boolean hasMob(){
        if (this.mobs != null){
            return true;
        }
        else{
        return false;
        }

    }
    // This is a method to check and see if an item is in the room.
    // You don't have to use this but it could be a good way to try to
    // pick up items by first checkecking to see if they are in the room.
    //public Item getItem(String objectName) {
        // Find the item in itemsHere and return it.
        //return itemsHere[0]; // this is not correct.
    //}
}
