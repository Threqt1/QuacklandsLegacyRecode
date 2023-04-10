package render;

import java.util.ArrayList;

public class Renderer {
    public Renderer() {

    }

    public String render(ArrayList<FullRenderable> renderables) {
        int maximumRenderWidth = 0;
        for (FullRenderable renderable : renderables) {
            maximumRenderWidth = Math.max(maximumRenderWidth, renderable.getDimensions().getX());
        }
        StringBuilder finalRender = new StringBuilder();
        StringBuilder border = new StringBuilder().append("|").append("-".repeat(maximumRenderWidth)).append("|");
        StringBuilder spacer = new StringBuilder().append("|").append(" ".repeat(maximumRenderWidth)).append("|");
        for (FullRenderable renderable : renderables) {
            StringBuilder renderableBuilder = new StringBuilder().append(border).append("\n").append(spacer).append("\n");
            String centerSpaces = " ".repeat((maximumRenderWidth - renderable.getDimensions().getX()) / 2);
            ArrayList<StringBuilder> renderedRenderable = renderable.render();
            for (StringBuilder piece : renderedRenderable) {
                renderableBuilder.append("|").append(centerSpaces).append(piece).append(centerSpaces).append("|\n");
            }
            renderableBuilder.append(spacer).append("\n").append(border).append("\n");
            finalRender.append(renderableBuilder);
        }
        return finalRender.toString();
    }
}
