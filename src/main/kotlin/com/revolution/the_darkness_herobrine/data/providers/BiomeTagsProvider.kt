package com.revolution.the_darkness_herobrine.data.providers

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.BiomeTagsProvider
import java.util.concurrent.CompletableFuture

class ModBiomeTagsProvider(output: PackOutput, provider: CompletableFuture<HolderLookup.Provider>)
    : BiomeTagsProvider(output, provider, TheDarknessHerobrine.ID) {
    override fun addTags(pProvider: HolderLookup.Provider) {
    }
}