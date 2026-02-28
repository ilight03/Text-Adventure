/*
 * CommandSystem.java
 * For use in the Final project for COSC 236.
 * Based on starter code first developed by Prof. Dastyni Loksa
 * Last updated for Spring 2024
 * 
 * This class is the primary logic class for the system. It defines what happens when valid commands are executed. 
 */

public class CommandSystem {
    private GameState state;
    private GameManager game;
    //private Player player;
    public boolean gameRunning;
    private boolean quitConfirmation; // New field to track quit confirmation
    private Location canMove;

    /*
     * Constructor here is used to define all verbs that can be used in the commands
     * and (perhaps) all nouns the user can interact with.
     * 
     * Suggestion: These could all be loaded from a file, or have a different
     * location that this happens. A different class or a new method in this class.
     * 
     * Verb descriptions are stored in a parallel Arraylist with the Verbs and are
     * used when printing out the help menu (using the ? command).
     * 
     * NOTEs:
     * 1. All nouns and verbs will be stored as lowercase.
     * 2. If you want a newline in a description string, use [nl] instead of \n
     */
    public CommandSystem(GameState state, GameManager game) {
        this.state = state;
        this.game = game;
        this.gameRunning = true;
        this.quitConfirmation = false; // Initialize quit confirmation

        // NOTE: Verb descriptions can use [nl] for new lines.
        // using \n will be ignored in them.
        game.addVerb("move","Use command with a direction like north to move in that direction.");
        game.addVerb("?", "Show this help screen.");
        game.addVerb("look",
                "Use the look command by itself to look in your current area. [nl]You can also look at a person or object by typing look and the name of what you want to look at.[nl]Example: look book");
        game.addVerb("l", "Same as the look command.");
        game.addVerb("quit", "Quit the game."); // NOTE: In the starter code, this is handled by the GameManager
        game.addVerb("get","Use get in a room with an item to place that item in your inventory to use");
        game.addVerb("use", "Use \"[ITEM]\" to use an item as long as it's in your inventory.");
        game.addVerb("leave", "Use this when at the forest exit.");
        game.addVerb("talk", "How can frogs even talk to anybody?");
        game.addVerb("kick", "Find the frog fu kick to start kicking people");
        game.addVerb("throw","pick your ammo and your target.");
        
    }

    

        

        public void kick(String target){
            if(state.currentLocation.hasMob()){
            game.print("You kicked "+target+" with incredible Frog Fu power!");
            state.currentLocation.mobs=null;
            game.print("You've defeated "+target+" with a single blow.");


            }
            else{
                game.print(target+" is too far to kick.");
            }
        }
        public void get(String itemName){

            
            if(state.currentLocation.name == "Forest Exit"){
                game.print("You've left the forest and have ended the game.");
                this.gameRunning = false;
            }
            if(state.currentLocation.item== null){
                game.print("There is no item to get here.");
                return;
                //state.currentLocation.item.addInventory() //this method will add the current locations item to the inventory[] and set currentLocation.item to null
            }
            switch(itemName){
                case "map":
                state.player.inventory[0]=state.currentLocation.item;
                state.currentLocation.item=null;
                game.print("You got a map!\nType \"use map\" to use the map.");
                break;
                case "trampoline":
                state.player.inventory[1]=state.currentLocation.item;
                state.currentLocation.item=null;
                game.print("You got a trampoline!\nType \"use trampoline\" to use the trampoline.");
                break;
                
            
                case "tnt":
                state.player.inventory[2]=state.currentLocation.item;
                state.currentLocation.item=null;
                game.print("You got tnt!\nType \"use tnt\" to use the tnt.");
                break;
                case "key":
                if(state.currentLocation.hasMob()){
                    game.print("The goblin isn't letting you get the key!");
                }
                state.player.inventory[3]=state.currentLocation.item;
                state.currentLocation.item=null;
                game.print("You got a seal breaker key!\nType \"use key\" to use the key.");
                break;
                default: game.print("You can't get that here.");

            }
            
            
        }

        public void use(String noun){
            boolean foundItem = false;
            String itemName = noun;
            for (int i = 0; i<state.player.inventory.length; i++){
                if(state.player.inventory[i]!= null && state.player.inventory[i].getName().equalsIgnoreCase(itemName)){
                    foundItem = true;
                    switch(itemName){
                        case "map":
                            game.print("The map shows a key symbol northeast of the bridge.");
                        break;
                        case "trampoline":
                            if(state.currentLocation.name == "Dilapidated Bridge"){
                            game.print("You jumped up super high across the bridge!");
                            state.currentLocation.n.canMove = true;
                            move("north");
                            }
                            else{
                                game.print("You jumped super high!");
                            }
                        break;

                        case "tnt":
                            game.print("BOOOOOOOM!!!");
                            game.print("You just blew yourself up, congrats!");
                            this.gameRunning = false;
                        break;

                        case "key":
                            game.print("use key on what?");
                        break;


                        
                    }
                }
                
            }
            if(!foundItem){
                game.print("You don't have that item.");
            }
        }


        public void talk(String noun){
            switch(noun){
                case "troll":
                    game.print("Hey frog!, here's a sacred frog kick scroll, should be able to kick anybody now. See ya!");
                    state.player.inventory[2]=state.currentLocation.item;
                    state.currentLocation.mobs=null;
                    game.print("You learned a frog kick!\nType kick to kick something.");
                break;

                case "guardian":
                    game.print("You shall not pass!");
                    break;
                
                case "goblin":
                    game.print("RAAAA");
                    break;
            }
        }
        
        public void move(String direction){
            
            
            switch (direction.toLowerCase()) {
                case "n":
                case "north":
                    
                    
                    if (state.currentLocation.hasMob()&&state.currentLocation.mobs.name == "Guardian Frog"){
                        game.print("You can't go north, a guardian blocks you.");
                    }
                    else if (state.currentLocation.n == null) {
                        game.print("You can't go north.");
                    
                    }
                    else if(state.currentLocation.n.canMove == false){
                        game.print("The bridge is broken, you can't move across...");
                    }
                     else {
                        state.currentLocation = state.currentLocation.n;
                        game.print("You moved north to " +state.currentLocation.name+". "+ state.currentLocation.description);
                    }
                    break;
                case "e":
                case "east":
                    if (state.currentLocation.e == null) {
                        game.print("You can't go east.");
                    //if(state.currentLocation.e == state.currentLocation.lookE){
                        //game.print("You cant just jump off the side of the bridge!");
                    //}
                    } else {
                        state.currentLocation = state.currentLocation.e;
                        game.print("You moved east to " + state.currentLocation.description);
                    }

                    break;
                case "s":
                case "south":
                    //if(state.currentLocation == state.currentLocation.exit) 
                    if (state.currentLocation.s == null) {
                        game.print("You can't go south.");
                    } else {
                        state.currentLocation = state.currentLocation.s;
                        game.print("You moved south to " + state.currentLocation.description);
                    }
                    break;
                case "w":
                case "west":
                    if (state.currentLocation.w == null) {
                        game.print("You can't go west.");
                    } else {
                        state.currentLocation = state.currentLocation.w;
                        game.print("You moved west to " + state.currentLocation.description);
                    }
                    break;
                default:
                    game.print("Not a valid direction.");

            }
       }     
    

    /**
     * Executes a command when it consists of just a verb
     *
     * @param verb The verb representing the action to be performed.
     */
    public void executeVerb(String verb) {
        switch (verb) {
            // REMINDER: All nouns and verbs are stored as lowercase.
            case "quit":
                        game.print("Exiting game");
                        this.gameRunning = false;
                break;

            case "l":
            case "look": // will show the description of the current room (stored in the state object)
                if(state.currentLocation.item.getName()=="kick"){
                    game.print(state.currentLocation.description);
                }
                else if(state.currentLocation.item!= null){
                    game.print("You see a " + state.currentLocation.item.getName() +"\n"+ state.currentLocation.item.getDesc());
                }
                else{
                game.print("You look around.");
                game.print(state.currentLocation.description);
                }

                break;
            case "?":
                game.printHelp();
                break;
            case "move":
                game.print("Add a direction after move, like move north");
            case "get":
                game.print("Get what?");
            case"leave":
                if(state.currentLocation.getName()=="Forest Exit"){
                    game.print("You succesfully left the forest.\n Exiting game...");
                    gameRunning = false;
                }
                else{
                    game.print("You can't leave from here, find the exit.");
                }
                break;
            case "talk":
                game.print("You talkin to yourself?");
            

        }
    }

    /**
     * Executes a command when it consists of a verb followed by a noun.
     *
     * @param verb The verb representing the action to be performed.
     * @param noun The noun representing the target of the action.
     */
    public void executeVerbNoun(String verb, String noun) {
        switch (verb) { // Decides what to do based on each verb
            case "l":
            case "look":
                game.print(lookAt(noun)); // The lookAt method will deal with what noun to look at.
                
                break;
            case "move":

                move(noun);
                
                
                break;
            
            case "get":
                if(state.currentLocation.hasMob()){
                    game.print("The goblin isn't letting you pick up the key!");
                }
                else{
                get(noun);}
                break;
            case "use":
                use(noun);

                break;
            case "talk":
            if(state.currentLocation.hasMob()){
                talk(noun);
                if(noun == "troll"){
                    state.currentLocation.mobs=null;
                }
            }
            else{
                game.print(noun+" is too far to hear you.");
            }
                break;
            case "kick":
            //if(noun=="troll") {
                //state.currentLocation.mobs=null;
                //state.currentLocation.setDesc("A dilapidated bridge, the gap is too far to move across.");
            //}
                kick(noun);
                break;

            
            
        }
    }

    // Used when command is a Verb followed by two nouns
    public void executeVerbNounNoun(String verb, String noun, String noun2) {
        // Remember, you can change this code however you would like. It's yours!
        switch (verb){
            case "use":
                switch(noun){
                    
                    case "trampoline":
                        switch(noun2){
                            case "guardian":
                            game.print("He's too big to jump over!");
                        }

                    
                    
                    case "key":
                        switch(noun2){
                            case "seal":
                                if(state.currentLocation.getName()=="Throne Room"){
                                    game.print("You've unsealed the king and saved the kingdom!\n");
                                    game.print("You're a frog fu knight now!");
                                    gameRunning=false;
                                }
                                else{
                                    game.print("You're too far away to do that.");
                                }
                                
                            break;
                        }
                    break;

                }
            case "throw":
                switch(noun){
                    case "tnt":
                        switch(noun2){
                            case "guardian":
                                game.print("You threw the tnt at the guardian and blew him up!\nYou also blew the whole castle up so, game over...");
                                gameRunning = false;
                                break;
                            case "troll":
                                game.print("You blew up the troll.. why?");
                        }

                }
            break;
        }
    }

    /**
     * Look method for handling the "look" command.
     * 
     * @param noun The noun representing the object to look at.
     * @return A string containing the description of the object being looked at, or
     *         an empty string if the object is not found.
     * 
     *         TODO: Return an error or message that says you don't see that if noun
     *         is not found instead of an empty string.
     */
    public String lookAt(String noun) {
        switch (noun) { // for the given verb, decide what to do based on what noun was entered
            case "mat":
                // get the description from the item you are looking at.
                return state.mat.getDesc();
            // Add more cases as needed for different objects
            default:
                return "You don't see that here.";
        }
    }

    /**
     * Processes the input command and determines the appropriate method to call
     * based on the number of words in the command.
     * 
     * @param command An array of strings representing the words in the command.
     */
    public void processCommand(String[] command) {
        switch (command.length) {
            case 1:
                executeVerb(command[0]);
                break;
            case 2:
                executeVerbNoun(command[0], command[1]);
                break;
            case 3:
                executeVerbNounNoun(command[0], command[1], command[2]);
                break;
        }
    }
}
