package com.tenjava.entries.OlsonElliott.t3;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    @Override
    public void onEnable(){
        getLogger().info("[HardcoreWorldGenerator] - v1.0.0 - Enabled");


    }

    @Override
    public void onDisable(){
        getLogger().info("[HardcoreWorldGenerator] - v1.0.0 - Disabled");

    }

    public ChunkGenerator getDefaultGenerator(String world, String id){
        return new FlatLandGenerator(this);
    }

    public static void setBiome(World world){
    }

    public static void setBlocks(byte[][] result, int x, int y, int z, byte blockId){
        int w;
        if (result[y>>4] == null){
            result[y>>4] = new byte[4096];
        }
        result[y>>4][((y&0xF)<<8) | (z << 4) | x] = blockId;
    }

    public static void setBlocksShort(short[][] result, int x, int y, int z, byte blockId){
        if (result[y>>4] == null){
            result[y>>4] = new short[4096];
        }
        result[y>>4][((y&0xF)<<8) | (z << 4) | x] = blockId;
    }
}
