package com.Cardinal.GTNHPregenerator.ChunkLoader;

import com.Cardinal.GTNHPregenerator.FileManager.PregeneratorFileManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.gen.ChunkProviderServer;
import org.apache.commons.lang3.tuple.Pair;


public class ChunkLoader
{
    PregeneratorFileManager fileManager;
    public ChunkLoader(PregeneratorFileManager fileManager)
    {
        this.fileManager = fileManager;
    }

    public void processLoadChunk(MinecraftServer server, int dimensionId, Pair<Integer, Integer> chunk) {
        int x = chunk.getLeft();
        int z = chunk.getRight();

        ChunkProviderServer cps = server.worldServerForDimension(dimensionId).theChunkProviderServer;
        cps.loadChunk(x, z, () -> {
            ChunkLoaderManager.instance.removeChunkFromList();
            this.fileManager.saveIteration(ChunkLoaderManager.instance.getChunkToLoadSize());
            System.out.println("Chunk at " + x + ", " + z + " loaded.");
        });
    }
}

