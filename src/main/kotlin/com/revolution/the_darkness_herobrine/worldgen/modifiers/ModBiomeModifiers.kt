package com.revolution.the_darkness_herobrine.worldgen.modifiers

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.worldgen.feature.ModPlacedFeatures
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.levelgen.GenerationStep
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier
import net.neoforged.neoforge.registries.NeoForgeRegistries


object ModBiomeModifiers {
    val LEAFLESS_OAK_MODIFIER = register("leafless_oak_modifier")
    val LEAFLESS_BIRCH_MODIFIER = register("leafless_birch_modifier")
    val LEAFLESS_SUPER_BIRCH_MODIFIER = register("leafless_super_birch_modifier")

    fun bootstrap(context: BootstrapContext<BiomeModifier>) {
        val placedFeature = context.lookup(Registries.PLACED_FEATURE)
        val biomes = context.lookup(Registries.BIOME)

        context.register(
            LEAFLESS_OAK_MODIFIER, AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_FOREST),
            HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LEAFLESS_OAK_TREE)),
            GenerationStep.Decoration.VEGETAL_DECORATION
        ))

        context.register(
            LEAFLESS_BIRCH_MODIFIER, AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_FOREST),
            HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LEAFLESS_BIRCH_TREE)),
            GenerationStep.Decoration.VEGETAL_DECORATION
        ))

        context.register(
            LEAFLESS_SUPER_BIRCH_MODIFIER, AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_FOREST),
            HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LEAFLESS_SUPER_BIRCH_TREE)),
            GenerationStep.Decoration.VEGETAL_DECORATION
        ))
    }

    private fun register(name: String): ResourceKey<BiomeModifier> {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, name))
    }
}