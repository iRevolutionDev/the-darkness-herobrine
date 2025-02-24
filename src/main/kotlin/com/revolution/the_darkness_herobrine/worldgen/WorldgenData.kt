package com.revolution.the_darkness_herobrine.worldgen

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.worldgen.feature.ModConfigFeature
import com.revolution.the_darkness_herobrine.worldgen.feature.ModPlacedFeatures
import com.revolution.the_darkness_herobrine.worldgen.modifiers.ModBiomeModifiers
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.concurrent.CompletableFuture

class WorldgenData(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : DatapackBuiltinEntriesProvider(output, registries, BUILDER, setOf(TheDarknessHerobrine.ID)) {
    companion object {
        val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfigFeature::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
    }
}