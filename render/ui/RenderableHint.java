package render.ui;

import engine.math.Vector2;
import render.FullRenderable;

import java.util.ArrayList;

public class RenderableHint implements FullRenderable {
    private ArrayList<String> hints;
    private Vector2 dimensions;

    public RenderableHint(ArrayList<String> hints) {
        this.hints = hints;
        int maximumHintWidth = 0;
        for (String hint : hints) {
            maximumHintWidth = Math.max(maximumHintWidth, hint.length());
        }
        dimensions = new Vector2(maximumHintWidth, hints.size());
    }

    public void updateHints(ArrayList<String> newHints) {
        this.hints = newHints;
        int maximumHintWidth = 0;
        for (String hint : newHints) {
            maximumHintWidth = Math.max(maximumHintWidth, hint.length());
        }
        dimensions = new Vector2(maximumHintWidth, newHints.size());
    }

    @Override
    public Vector2 getDimensions() {
        return dimensions;
    }

    @Override
    public ArrayList<StringBuilder> render() {
        ArrayList<StringBuilder> renderedHints = new ArrayList<>();
        for (String hint : hints) {
            String centerSpaces = " ".repeat((dimensions.getX() - hint.length()) / 2);
            renderedHints.add(new StringBuilder().append(centerSpaces).append(hint).append(centerSpaces));
        }
        return renderedHints;
    }
}
