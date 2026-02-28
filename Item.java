import java.util.*;

/*
Item.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This class represents an item that can be interacted with by the player.  
*/
public class Item {
    private String name;
    private String description;
    private int damage;
    private int endurance;
    private String image;
    

   

    public Item(String n, String d) {
        this.name = n;
        this.description = d;
     
    }
    public Item(String n, String d, int dmg, int dur) {
        this.name = n;
        this.description = d;
        this.damage = dmg;
        this.endurance = dur;

    }

    public String getDesc() {
        return description;
    }
    public String getName(){
        return name;
    }
    public void upgrade(){
        damage+= 1000;
        endurance += 1000;
    }
    public void itemBreak(){
        endurance = 0;
        damage = 0;
    }

    public void doDmg(){
        //will coordinate with health of mob object to -= 
    }
}
