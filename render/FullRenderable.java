package render;

import engine.math.Vector2;

import java.util.ArrayList;

public interface FullRenderable {
    Vector2 getDimensions();
    ArrayList<StringBuilder> render();
}
