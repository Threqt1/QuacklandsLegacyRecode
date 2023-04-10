package engine;

import game.Game;
import render.Renderer;
import utils.ASCII;
import utils.MapCache;

import java.util.Scanner;

/**
 * The engine, which handles the overarching game loop
 */
public class Engine {
    public static MapCache mapCache = new MapCache();

    private final GameLogic logic;
    private final Renderer renderer;

    public Engine() {
        this.logic = new Game();
        this.renderer = new Renderer();
    }

    /**
     * Start the game
     */
    public void start() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            //Poll Interactions
            logic.updateInteractions();
            //Render
            System.out.print(renderer.render(logic.getRenderables()));
            //Get Input
            String inputLine = inputScanner.nextLine().trim().toUpperCase();
            //Clear Screen
            System.out.println(ASCII.CLEAR_CODE);
            System.out.flush();
            //Handle Input
            for (String input : inputLine.split("")) {
                logic.handleInput(input);
            }
        }
    }
}
