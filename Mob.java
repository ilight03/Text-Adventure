public class Mob {
//State of my mobs
String name;
String description;
int health;
int damage;
String phrase; // will need constructor to populate each object of Mob class with unique phrases

//Constructor
public Mob(String name, String phrase){
    this.name = name;
    this.phrase = phrase;
}



//Other methods/behaviors for mobs to have 
public int getHealth(){
    return health;
}

public String getDesc(){
    return description;
}

public void doDmg(){
    //needs to act on player objects current health
}

public int setHealth(int damage){ // coordinates with dealDmg of item class
    health -= damage;
    return health;
}

public String getName(){
    return name;
}




}
