package fr.rhumun.game.worldcraftopengl.worlds.structures;

import fr.rhumun.game.worldcraftopengl.worlds.World;

public abstract class AbstractStructure {

    private final int length;
    private final int width;

    public AbstractStructure(int length, int width){
        this.width = width;
        this.length = length;
    }

    public void tryBuildAt(World world, int x, int y, int z){
        //System.out.println("trying to build at " + x + " " + z);
        int length = this.length/2;
        int width = this.width/2;
        if(world.isChunkLoadedAt(x+length, z+width) &&
                world.isChunkLoadedAt(x-length, z+width) &&
                world.isChunkLoadedAt(x+length, z-width) &&
                world.isChunkLoadedAt(x-length, z-width))
            buildAt(world, x, y ,z);
    }

    public abstract void buildAt(World world, int x, int y, int z);
}
