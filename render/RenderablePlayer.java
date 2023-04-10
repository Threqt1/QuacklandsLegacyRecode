package render;

import game.Player;

public class RenderablePlayer implements PartialRenderable {
    private Player player;
    public RenderablePlayer(Player player) {
        this.player = player;
    }
    @Override
    public void renderOntoOngoingBuilder(StringBuilder stringBuilder) {
        stringBuilder.insert(stringBuilder.indexOf("\n"), "👑");
        stringBuilder.append("🦆");
    }
}
