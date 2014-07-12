package com.tenjava.entries.OlsonElliott.t3;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

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
public class TerrainGeneration extends ChunkGenerator{

    void setBlock(int x, int y, int z, byte[][] chunk, Material material) {

        if (chunk[y >> 4] == null)
            chunk[y >> 4] = new byte[16 * 16 * 16];
        if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
            return;
        try {
            chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material
                    .getId();
        } catch (Exception e) {

        }
    }

    byte getBlock(int x, int y, int z, byte[][] chunk) {
        if (chunk[y >> 4] == null)
            return 0;
        if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
            return 0;
        try {
            return chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public byte[][] generateBlockSections(World world, Random rand, int ChunkX,
                                          int ChunkZ, BiomeGrid biome) {
        byte[][] chunk = new byte[world.getMaxHeight() / 16][];

        SimplexOctaveGenerator overhangs = new SimplexOctaveGenerator(world,8);
        SimplexOctaveGenerator bottoms = new SimplexOctaveGenerator(world,8);

        overhangs.setScale(1/64.0);
        bottoms.setScale(1/128.0);

        int overhangsMagnitude = 16;
        int bottomsMagnitude = 32;

        for (int x=0; x<16; x++) {
            for (int z=0; z<16; z++) {
                int realX = x + ChunkX * 16;
                int realZ = z + ChunkZ * 16;

                int bottomHeight = (int) (bottoms.noise(realX, realZ, 0.5, 0.5) * bottomsMagnitude + 64);
                int maxHeight = (int) overhangs.noise(realX, realZ, 0.5, 0.5) * overhangsMagnitude + bottomHeight + 32;
                double threshold = 0.3;

                //This FOR statement controls the terrain control.
                for (int y=0; y<maxHeight; y++) {
                    if (y > bottomHeight) { //This segment controls the overhangs of the World Generation.
                        double density = overhangs.noise(realX, y, realZ, 0.5, 0.5);

                        if (density > threshold) setBlock(x,y,z,chunk,Material.STONE);

                    } else {
                        setBlock(x,y,z,chunk,Material.STONE);
                    }
                }

                //Handle the grass blocks of the overhang.
                setBlock(x,bottomHeight,z,chunk,Material.GRASS);
                setBlock(x,bottomHeight - 1,z,chunk,Material.DIRT);
                setBlock(x,bottomHeight - 2,z,chunk,Material.DIRT);

                for (int y=bottomHeight + 1; y>bottomHeight && y < maxHeight; y++ ) { //Continue the handle of the Overhang
                    int thisblock = getBlock(x, y, z, chunk);
                    int blockabove = getBlock(x, y+1, z, chunk);

                    if(thisblock != Material.AIR.getId() && blockabove == Material.AIR.getId()) {
                        setBlock(x, y, z, chunk, Material.GRASS);
                        if(getBlock(x, y-1, z, chunk) != Material.AIR.getId())
                            setBlock(x, y-1, z, chunk, Material.DIRT);
                        if(getBlock(x, y-2, z, chunk) != Material.AIR.getId())
                            setBlock(x, y-2, z, chunk, Material.DIRT);
                    }
                }

            }
        }
        return chunk;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        ArrayList<BlockPopulator> pops = new ArrayList<BlockPopulator>();
        return pops;
    }

}
