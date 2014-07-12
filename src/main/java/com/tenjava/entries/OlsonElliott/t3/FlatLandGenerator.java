package com.tenjava.entries.OlsonElliott.t3;

import com.tenjava.entries.OlsonElliott.t3.Populators.TreePopulator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class FlatLandGenerator extends ChunkGenerator {

    Core plugin;

    List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

    public FlatLandGenerator(Core instnace){
        plugin = instnace;
        populators.add(new TreePopulator());
    }

    public List<BlockPopulator> getDefaultPopulators(World world){
        return populators;
    }

    public Location getFixedSpawnLocation(World world, Random random){
        return new Location(world, 0, 0, 0);
    }

    public byte[][] generatorBlockSections(World world, Random random,
                      int Chunkx, int Chunky, BiomeGrid biomeGrid){

        byte[][] result = new byte[256 / 16][];
        int x, y, z;
        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                Core.setBlocks(result, x, 0, z, (byte) Material.BEDROCK.getId());
            }
        }
        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                for (y = 1; y <= 2; y++){
                    Core.setBlocks(result, x, y, z, (byte) Material.DIRT.getId());
                }
            }
        }

        //Diamond Ore
        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                for (y = 2; y <= 7; y++){
                    if (random.nextInt(120) > 105){
                        Core.setBlocks(result, x, y, z, (byte) Material.DIAMOND_ORE.getId());
                    } else {
                        if (random.nextInt(150) > 130){
                            Core.setBlocks(result, x, y, z, (byte) Material.LAVA.getId());
                        } else {
                            if (random.nextInt(50) > 35){
                                Core.setBlocks(result, x, y, z, (byte) Material.OBSIDIAN.getId());
                            } else {
                                Core.setBlocks(result, x, y, z, (byte) Material.STONE.getId());
                            }
                        }
                    }
                }
            }
        }

        //Desert Ontop
        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                for (y = 8; y <= 14; y++){
                    if (random.nextInt(100) > 75){
                        Core.setBlocks(result, x, y, z, (byte) Material.SAND.getId());
                    } else {
                        if (random.nextInt(140) > 90){
                            Core.setBlocks(result, x, y, z, (byte) Material.WATER.getId());
                        } else {
                            Core.setBlocks(result, x, y, z, (byte) Material.CACTUS.getId());
                        }
                    }
                }
            }
        }

        return result;
    }

    public short[][] getExtBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomes){
        short[][] result = new short[256 / 16][];
        int x, y, z;

        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                Core.setBlocksShort(result, x, 0, z, (byte) Material.BEDROCK.getId());
            }
        }
        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                for (y = 1; y <= 2; y++){
                    Core.setBlocksShort(result, x, y, z, (byte) Material.DIRT.getId());
                }
            }
        }
        for (x = 0; x < 16; x++){
            for (z = 0; z <16; z++){
                for (y = 2; y <= 3; y++){
                    Core.setBlocksShort(result, x, y, z, (byte) Material.GRASS.getId());
                }
            }
        }
        return result;
    }


}
