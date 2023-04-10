package render.game;

import game.Player;
import render.PartialRenderable;

public class RenderablePlayer implements PartialRenderable {
    private final Player player;
    public RenderablePlayer(Player player) {
        this.player = player;
    }
    @Override
    public void renderOntoOngoingBuilder(StringBuilder stringBuilder) {
        stringBuilder.insert(stringBuilder.indexOf("\n"), "👑");
        stringBuilder.append("🦆");
    }
}
