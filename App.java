/* UML document for starter code:
https://lucid.app/lucidchart/d4811d64-ce6c-433b-ad15-e3ec444905aa/edit?invitationId=inv_e7cfa342-d34f-4a83-9c68-98065a45cfe4&page=0_0#

App.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa
Last updated for Spring 2024

This is the driver class of the project. It is in charge of the following:

- Setting up the game by creating the GameState, GameManager, and CommandSystem objects.
- Starting the game
- The main game loop deciding when the game quits.
- The input from the user and routing to the correct methods in the CommandSystem.

*/

import java.util.*;

public class App {
    static Scanner in = new Scanner(System.in);

    static GameState state;
    static GameManager game;
    static CommandSystem commandSystem;

    public static void main(String[] args) throws Exception {

        // Store the command system for easy reference in the client code.
        game = new GameManager();
        state = new GameState(game);
        commandSystem = new CommandSystem(state, game);

        // Game introductions could go here!

       commandSystem.gameRunning = true; // This controls if the game should continue running.
        // If you put this somewhere else, it might be easy to change from other files
        //put where command system has access

        // The main game loop.
        while (commandSystem.gameRunning) {

            // Gets input from the user in an array of strings that they typed in.
            String[] command = getCommand();

            // if the game knows about the verb and all the nouns, we should process that
            // command!
            // The validCommand method will output errors if ti is not valid.
            if (game.validCommand(command)) {
                commandSystem.processCommand(command);
            }

        }

    }

    // Gets input from the user
    // seperates the input into each word (determined by whitespace)
    // returns an array with each word an element of the array.
    public static String[] getCommand() {

        game.print(GameManager.lineBreak, false, false);
        System.out.print("What would you like to do?\n> ");
        String input = in.nextLine();
        game.print("");

        return input.toLowerCase().split("\\s+");
    }

}