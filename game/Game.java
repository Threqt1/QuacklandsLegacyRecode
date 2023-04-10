package game;

import engine.Engine;
import engine.math.Vector2;
import parsers.records.ParsedMap;
import render.FullRenderable;
import render.RenderableGame;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private final Player player;
    private final Map map;
    private final RenderableGame renderableGame;

    public Game() {
        this.player = new Player();
        this.map = new Map();
        this.renderableGame = new RenderableGame(this);
    }

    public void setNewMap(String newMapID) {
        ParsedMap newMap = Engine.mapCache.getRawMapData(newMapID);
        map.loadMapData(newMap);
        player.setPosition(newMap.startingPosition());
    }

    public void movePlayer(Vector2 direction) {
        Vector2 newPosition = player.getPosition().add(direction);
        if (map.isWalkable(newPosition)) {
            player.setPosition(newPosition);
        }
    }

    public ArrayList<FullRenderable> getRenderables() {
        return new ArrayList<>(Arrays.asList(renderableGame));
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }
}
