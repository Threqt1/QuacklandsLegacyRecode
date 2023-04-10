package game;

import engine.math.Vector2;

public class Player {
    private Vector2 position;

    public Player() {
        this.position = new Vector2(0, 0);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
