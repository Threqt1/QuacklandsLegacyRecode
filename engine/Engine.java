package engine;

import engine.math.Vector2;
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
    public static final String DEFAULT_MAP = "test";

    private final Game game;
    private final Renderer renderer;

    public Engine() {
        this.game = new Game();
        game.setNewMap(DEFAULT_MAP);
        this.renderer = new Renderer();
    }

    /**
     * Start the game
     */
    public void start() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            //Render
            System.out.print(renderer.render(game.getRenderables()));
            //Get Input
            String inputLine = inputScanner.nextLine().trim().toUpperCase();
            //Clear Screen
            System.out.println(ASCII.CLEAR_CODE);
            System.out.flush();
            //Handle Input
            for (String input : inputLine.split("")) {
                handleInput(input);
            }
        }
    }

    public void handleInput(String input) {
        switch (input) {
            case Controls.MOVE_UP -> game.movePlayer(Vector2.UP);
            case Controls.MOVE_DOWN -> game.movePlayer(Vector2.DOWN);
            case Controls.MOVE_LEFT -> game.movePlayer(Vector2.LEFT);
            case Controls.MOVE_RIGHT -> game.movePlayer(Vector2.RIGHT);
        }
    }
}
