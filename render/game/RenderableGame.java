package render.game;

import engine.Engine;
import engine.math.Vector2;
import game.Game;
import render.FullRenderable;
import render.PartialRenderable;

import java.util.ArrayList;
import java.util.HashMap;

public class RenderableGame implements FullRenderable {
    private final Game game;
    private final RenderablePlayer renderablePlayer;
    private final HashMap<String, RenderableTile> tileCache;

    public RenderableGame(Game game) {
        this.game = game;
        this.renderablePlayer = new RenderablePlayer(game.getPlayer());
        this.tileCache = new HashMap<>();
    }

    @Override
    public ArrayList<StringBuilder> render() {
        String[][] map = Engine.mapCache.getRawMapData(game.getMap().getId()).map();
        PartialRenderable[][] renderableMap = new PartialRenderable[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                renderableMap[i][j] = tileCache.computeIfAbsent(map[i][j], RenderableTile::new);
            }
        }

        Vector2 playerPosition = game.getPlayer().getPosition();
        renderableMap[playerPosition.getY()][playerPosition.getX()] = renderablePlayer;

        ArrayList<StringBuilder> renderedMap = new ArrayList<>();
        for (PartialRenderable[] row : renderableMap) {
            StringBuilder rowBuilder = new StringBuilder();
            rowBuilder.append("\n");
            for (PartialRenderable partialRenderable : row) {
                partialRenderable.renderOntoOngoingBuilder(rowBuilder);
            }
            String[] bothParts = rowBuilder.toString().split("\n");
            for (String part : bothParts) {
                renderedMap.add(new StringBuilder(part));
            }
        }

        return renderedMap;
    }

    @Override
    public Vector2 getDimensions() {
        String[][] map = Engine.mapCache.getRawMapData(game.getMap().getId()).map();
        return new Vector2(map[0].length * 2, map.length * 2);
    }
}
