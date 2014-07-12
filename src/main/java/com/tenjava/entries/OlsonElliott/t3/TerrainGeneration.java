package com.tenjava.entries.OlsonElliott.t3;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class TerrainGeneration extends ChunkGenerator{

    public static void setBlock(int x, int y, int z, short[][] chunk, Material material){
        if (y < 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0){
            if (chunk[y >> 4] == null){
                chunk[y >> 4] = new short[16 * 16 * 16];
                chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (short) material.getId();
            }
        }
    }

    @Override
    public byte[][] generateBlockSections(World world, Random random, int ChunkX, int ChunkZ, BiomeGrid biomeGrid){
        return null;
    }

}
