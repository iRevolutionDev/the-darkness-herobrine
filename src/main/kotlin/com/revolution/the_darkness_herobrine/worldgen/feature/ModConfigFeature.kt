package com.revolution.the_darkness_herobrine.worldgen.feature

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer


object ModConfigFeature {
    val LEAFLESS_OAK_TREE = registerKey("leafless_oak_tree")
    val LEAFLESS_BIRCH_TREE = registerKey("leafless_birch_tree")
    val LEAFLESS_SUPER_BIRCH_TREE = registerKey("leafless_super_birch_tree")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        register(
            context,
            LEAFLESS_OAK_TREE,
            Feature.TREE,
            createLeaflessOak().build()
        )

        register(
            context,
            LEAFLESS_BIRCH_TREE,
            Feature.TREE,
            createLeaflessBirch().build()
        )

        register(
            context,
            LEAFLESS_SUPER_BIRCH_TREE,
            Feature.TREE,
            createLeaflessSuperBirch().build()
        )
    }

    private fun createStraightBlobTree(
        logBlock: Block, baseHeight: Int, heightRandA: Int, heightRandB: Int, radius: Int
    ): TreeConfigurationBuilder {
        return TreeConfigurationBuilder(
            BlockStateProvider.simple(logBlock),
            StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
            BlockStateProvider.simple(Blocks.AIR),
            BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
            TwoLayersFeatureSize(1, 0, 1)
        )
    }

    private fun createLeaflessOak(): TreeConfigurationBuilder {
        return createStraightBlobTree(Blocks.OAK_LOG, 4, 2, 0, 2).ignoreVines()
    }

    private fun createLeaflessBirch(): TreeConfigurationBuilder {
        return createStraightBlobTree(Blocks.BIRCH_LOG, 5, 2, 0, 2).ignoreVines()
    }

    private fun createLeaflessSuperBirch(): TreeConfigurationBuilder {
        return createStraightBlobTree(Blocks.BIRCH_LOG, 5, 2, 6, 2).ignoreVines()
    }

    private fun registerKey(name: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, name)
        )
    }

    private fun <FC : FeatureConfiguration?, F : Feature<FC>?> register(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>, feature: F & Any, configuration: FC & Any
    ) {
        context.register(key, ConfiguredFeature(feature, configuration))
    }
}