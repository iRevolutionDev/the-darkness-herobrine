package com.revolution.the_darkness_herobrine.worldgen.feature

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*


object ModPlacedFeatures {
    val LEAFLESS_OAK_TREE = registerKey("leafless_oak_tree")
    val LEAFLESS_BIRCH_TREE = registerKey("leafless_birch_tree")
    val LEAFLESS_SUPER_BIRCH_TREE = registerKey("leafless_super_birch_tree")

    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE)

        register(
            context,
            LEAFLESS_OAK_TREE,
            configuredFeature.getOrThrow(ModConfigFeature.LEAFLESS_OAK_TREE),
            listOf(
                PlacementUtils.countExtra(0, 0.05f, 1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)
                ),
                BiomeFilter.biome()
            )
        )

        register(
            context,
            LEAFLESS_BIRCH_TREE,
            configuredFeature.getOrThrow(ModConfigFeature.LEAFLESS_BIRCH_TREE),
            listOf(
                PlacementUtils.countExtra(0, 0.05f, 1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)
                ),
                BiomeFilter.biome()
            )
        )

        register(
            context,
            LEAFLESS_SUPER_BIRCH_TREE,
            configuredFeature.getOrThrow(ModConfigFeature.LEAFLESS_SUPER_BIRCH_TREE),
            listOf(
                PlacementUtils.countExtra(0, 0.05f, 1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)
                ),
                BiomeFilter.biome()
            )
        )
    }

    private fun registerKey(name: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, name))
    }

    private fun register(context: BootstrapContext<PlacedFeature>, key: ResourceKey<PlacedFeature>, config: Holder<ConfiguredFeature<*, *>>, modifiers: List<PlacementModifier>): Holder.Reference<PlacedFeature> {
        return context.register(key, PlacedFeature(config, modifiers))
    }
}