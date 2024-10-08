package fr.rhumun.game.worldcraftopengl.controls;

import fr.rhumun.game.worldcraftopengl.Game;
import org.lwjgl.glfw.GLFWScrollCallback;

import static fr.rhumun.game.worldcraftopengl.Game.GAME;

public class Scroll extends GLFWScrollCallback {
    @Override
    public void invoke(long window, double xoffset, double yoffset) {
        Game game = GAME;

        game.getPlayer().setSelectedSlot((game.getPlayer().getSelectedSlot()+1)%9);
    }
}
