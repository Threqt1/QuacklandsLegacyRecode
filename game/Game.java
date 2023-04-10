package game;

import engine.Controls;
import engine.Engine;
import engine.GameLogic;
import engine.math.Vector2;
import parsers.records.ParsedMap;
import parsers.records.ParsedMapTile;
import parsers.records.ParsedTeleportInfo;
import render.FullRenderable;
import render.game.RenderableGame;
import render.ui.RenderableHint;
import utils.Dialogue;

import java.util.ArrayList;

public class Game implements GameLogic {
    public static final String DEFAULT_MAP = "Bartolomew_Room";

    private final Player player;
    private final Map map;
    private final RenderableGame renderableGame;
    private final RenderableHint renderableHint;
    private final ArrayList<String> hints;
    private Runnable interactionCallback;

    public Game() {
        this.player = new Player();
        this.map = new Map();
        setNewMap(DEFAULT_MAP);
        this.renderableGame = new RenderableGame(this);
        this.hints = new ArrayList<>();
        this.renderableHint = new RenderableHint(hints);
        this.interactionCallback = () -> {};
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case Controls.MOVE_UP -> movePlayer(Vector2.UP);
            case Controls.MOVE_DOWN -> movePlayer(Vector2.DOWN);
            case Controls.MOVE_LEFT -> movePlayer(Vector2.LEFT);
            case Controls.MOVE_RIGHT -> movePlayer(Vector2.RIGHT);
            case Controls.INTERACT -> interactionCallback.run();
        }
    }

    @Override
    public void updateInteractions() {
        hints.clear();
        interactionCallback = () -> {};
        ParsedMapTile tileInfo = map.getTileInfo(player.getPosition());
        if (tileInfo.canTeleportOn()) {
            hints.add(Dialogue.teleportDialogue(Controls.INTERACT, Dialogue.normalizeMapId(tileInfo.teleportInfo().newMapId()).toUpperCase()));
            interactionCallback = () -> teleportTo(tileInfo.teleportInfo());
        }
    }

    public void teleportTo(ParsedTeleportInfo teleportInfo) {
        setNewMap(teleportInfo.newMapId());
        player.setPosition(teleportInfo.newPlayerPosition());
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

    @Override
    public ArrayList<FullRenderable> getRenderables() {
        ArrayList<FullRenderable> renderables = new ArrayList<>();
        renderables.add(renderableGame);
        if (hints.size() > 0) {
            renderableHint.updateHints(hints);
            renderables.add(renderableHint);
        }
        return renderables;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }
}
