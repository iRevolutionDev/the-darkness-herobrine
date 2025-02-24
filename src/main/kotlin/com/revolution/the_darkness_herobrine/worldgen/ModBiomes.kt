package com.revolution.the_darkness_herobrine.worldgen

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome

object ModBiomes {
    val DRY_FOREST = registerKey("dry_forest")

    fun bootstrap(context: BootstrapContext<Biome>) {
        val caverGetter = context.lookup(Registries.CONFIGURED_CARVER)
        val placedFeature = context.lookup(Registries.PLACED_FEATURE)

        register(context, DRY_FOREST, ModBiomeCreator.createDryForest(placedFeature, caverGetter))
    }

    private fun registerKey(name: String): ResourceKey<Biome> {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, name))
    }

    private fun register(context: BootstrapContext<Biome>, key: ResourceKey<Biome>, biome: Biome) {
        context.register(key, biome)
    }
}