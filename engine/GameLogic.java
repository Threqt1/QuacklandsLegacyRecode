package engine;

import render.FullRenderable;

import java.util.ArrayList;

public interface GameLogic {
    void handleInput(String input);
    void updateInteractions();
    ArrayList<FullRenderable> getRenderables();
}
