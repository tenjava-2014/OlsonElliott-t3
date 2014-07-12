package com.tenjava.entries.OlsonElliott.t3;

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

    public ChunkGenerator getDeafulatWorldGenerator(String world, String id){
        return new FlatLandGenerator();
    }


}
