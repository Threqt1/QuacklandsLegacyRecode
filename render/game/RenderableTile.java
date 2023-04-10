package render.game;

import render.PartialRenderable;

public class RenderableTile implements PartialRenderable {
    private final String tile;

    public RenderableTile(String tile) {
        this.tile = tile;
    }

    @Override
    public void renderOntoOngoingBuilder(StringBuilder stringBuilder) {
        String repeatedTile = tile.repeat(2);
        stringBuilder.insert(stringBuilder.indexOf("\n"), repeatedTile);
        stringBuilder.append(repeatedTile);
    }
}
