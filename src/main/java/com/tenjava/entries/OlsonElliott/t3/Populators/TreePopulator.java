package com.tenjava.entries.OlsonElliott.t3.Populators;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class TreePopulator extends BlockPopulator{

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int cX = chunk.getX() * 16;
        int cZ = chunk.getZ() * 16;
        int cZOff = cX + random.nextInt(10);
        int cXOff = cZ + random.nextInt(12);

        if (random.nextInt(150) > 55){
            if (world.getBiome(cXOff, cZOff) == Biome.EXTREME_HILLS_PLUS_MOUNTAINS){
                world.generateTree(new Location(world, cXOff,
                        world.getHighestBlockYAt(cXOff, cZOff), cZOff), TreeType.DARK_OAK);
            } else if(world.getBiome(cXOff, cZOff) == Biome.EXTREME_HILLS_MOUNTAINS){
                world.generateTree(new Location(world, cXOff,
                        world.getHighestBlockYAt(cXOff, cZOff), cZOff), TreeType.BIG_TREE);
            } else if (world.getBiome(cXOff, cZOff) == Biome.DESERT){
                world.generateTree(new Location(world, cXOff,
                        world.getHighestBlockYAt(cXOff, cZOff), cZOff), TreeType.TALL_REDWOOD);
            } else if (world.getBiome(cXOff, cZOff) == Biome.EXTREME_HILLS_PLUS_MOUNTAINS){
                world.generateTree(new Location(world, cXOff,
                        world.getHighestBlockYAt(cXOff, cZOff), cZOff), TreeType.DARK_OAK);
            } else if (world.getBiome(cXOff, cZOff) == Biome.SWAMPLAND){
                world.generateTree(new Location(world, cXOff,
                        world.getHighestBlockYAt(cXOff, cZOff), cZOff), TreeType.COCOA_TREE);
            }
        }
    }
}
