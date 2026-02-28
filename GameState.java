/*
GameState.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa
Last updated for Spring 2024

This is the class to hold the state of the running game and allows easy
access to important information about the state of the game.
*/

public class GameState {
    
    Location currentLocation;
    Item mat;
    Player player ;
    /*
     * GameState Constructor
     * 
     * Ideally, your game will be fully loaded and ready to play once this
     * constructor has finished running.
     * 
     * How things have been done here are just a rudementry setup to link the other
     * classes and have the
     * bare bones example of the command system. This is not a great way to
     * initilize your project.
     * 
     * You should do better!
     */

    public GameState(GameManager game) {

        player = new Player();
        Item jumpScroll = new Item("trampoline","it's a mini frog trampoline that lets you jump super high!",0,1000);
        Item map = new Item(" map","Use map to view the map.");
        Item kick = new Item("kick", "FROG FU KICK HIYA");
        //Item Trollkick = new Item("boot","Troll Fu Scroll that allows for a devestating kick!",1000,1000);
        Item tnt = new Item("tnt","Explosives, use or throw to unleash an enormous explosion",10000, 1000);
        Item key = new Item("key","An unsealing scroll, you can use this on the kings seal!.",0,0);

        Location forestMain = new Location("Forest","Surrounded by the thick forest, there are trails to the east and west. \n\nA light is shining through to the north. ",null);
        Location forestLeft = new Location("Forest left","This is quiet part of the forest\n \"Looks like a good place to hone my skills\" ",jumpScroll);
        Location forestRight = new Location("Forest right","Looks like an abandonded camp. \n \"Maybe they left something behind?\"",map);
        Location forestExit = new Location("Forest Exit","Its the exit! Leaving will end the game.", null );
        
        Mob guardian = new Mob("Guardian Frog", "You shall not pass!");
        Mob troll = new Mob("Troll Frog", "Hey frog!, here's a sacred frog kick scroll, should be able to kick anybody now. See ya!");
        Mob goblin = new Mob("Key goblin", "GRAAAA");
       
        
        Location bridgeMain = new Location ("Dilapidated Bridge","A dilapidated bridge, the gap is too far to move across. There's a troll underneath.\n \"Wonder if he'll say hello\"",kick);
        
        //if(!bridgeMain.hasMob()){
            //bridgeMain.setDesc("The bridge is half collapsed... our troll friend is gone.");
        //}
        
        //Location bridgeViewLeft = new Location("the left side of the bridge", "You can see the enormous mountain ranges from here.",null);
        //Location bridgeViewRight = new Location("the right side of the bridge", "You can see the river flowing down from the mountains through to the ocean.",null);
        
        Location castleMain = new Location("Castle Keep", "Main castle area, the throne room is blocked by a guardian.",null);
        Location treasure = new Location("Treasure Room","There's a goblin with an unsealing key!",key);
        Location throne = new Location("Throne Room","The king is sealed right in front of you!",null );
        Location weapons = new Location("Armory","Look like some explosives",tnt);       
        castleMain.mobs = guardian;
        bridgeMain.mobs = troll;
        treasure.mobs = goblin;  


        
        

        castleMain.canMove = false;
        
        forestMain.s = forestExit;
        forestMain.n = bridgeMain;
        forestMain.e = forestRight;
        forestMain.w = forestLeft;
        
        forestRight.w = forestMain;
        
        forestLeft.e = forestMain;
        
        bridgeMain.s = forestMain;
        bridgeMain.n = castleMain;
        //bridgeMain.e = bridgeViewRight;
        //bridgeViewRight.w = bridgeMain;
        //bridgeMain.w = bridgeViewLeft;
        //bridgeViewLeft.e = bridgeMain;

        castleMain.n = throne;
        castleMain.e = treasure;
        castleMain.s = bridgeMain;
        castleMain.w = weapons;

        throne.s = castleMain;

        treasure.w = castleMain;
        weapons.e = castleMain;

        // Create first (starting) location
        // (and store it in currentLocation so I can always referece where the player is
        // easily)
        currentLocation = forestMain;
        

        // Create a demo item.
        mat = new Item("Mat", "There is a welcome mat here. This is a special mat.",0,1000);
        
        //currentLocation.addItem(mat);

        // Add item to list of nouns so our command system knows it exists.
        game.addNoun("n");
        game.addNoun("north");
        game.addNoun("e");
        game.addNoun("east");
        game.addNoun("s");
        game.addNoun("south");
        game.addNoun("w");
        game.addNoun("west");
        game.addNoun("map");
        game.addNoun("trampoline");
        game.addNoun("tnt");
        game.addNoun("key");
        game.addNoun("seal");
        game.addNoun("bridge");
        game.addNoun("guardian");
        game.addNoun("troll");
        game.addNoun("goblin");
    

        /*
         * Once the commandSystem knows about the item, we need to code what happens
         * with each of the commands that can happen with the item.
         * See CommandSystem line 96 for what happens if you currently "look mat"
         */
    }
}
