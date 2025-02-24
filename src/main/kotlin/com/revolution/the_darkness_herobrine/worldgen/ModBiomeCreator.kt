package com.revolution.the_darkness_herobrine.worldgen

import com.revolution.the_darkness_herobrine.worldgen.feature.ModPlacedFeatures
import net.minecraft.core.HolderGetter
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.biome.OverworldBiomes
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.Music
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature


object ModBiomeCreator {
    fun getSkyColor(temperature: Float): Int {
        var f = temperature / 3.0f
        f = Mth.clamp(f, -1.0f, 1.0f)
        return Mth.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f)
    }

    private fun biome(
        hasPrecipitation: Boolean,
        temperature: Float,
        downfall: Float,
        waterColor: Int,
        spawnBuilder: MobSpawnSettings.Builder,
        biomeBuilder: BiomeGenerationSettings.Builder,
        music: Music?
    ): Biome {
        return BiomeBuilder()
            .hasPrecipitation(hasPrecipitation)
            .temperature(temperature)
            .downfall(downfall)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(waterColor)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .skyColor(getSkyColor(temperature))
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(music).build()
            )
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build())
            .build()
    }

    private fun globalOverworldGeneration(pGenerationSettings: BiomeGenerationSettings.Builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(pGenerationSettings)
        BiomeDefaultFeatures.addDefaultCrystalFormations(pGenerationSettings)
        BiomeDefaultFeatures.addDefaultMonsterRoom(pGenerationSettings)
        BiomeDefaultFeatures.addDefaultUndergroundVariety(pGenerationSettings)
        BiomeDefaultFeatures.addDefaultSprings(pGenerationSettings)
        BiomeDefaultFeatures.addSurfaceFreezing(pGenerationSettings)
    }

    fun createDryForest(placedFeatureGetter: HolderGetter<PlacedFeature>, carverGetter: HolderGetter<ConfiguredWorldCarver<*>>): Biome {
        val spawnBuilder = MobSpawnSettings.Builder()
        spawnBuilder.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.DONKEY, 1, 1, 2))
        spawnBuilder.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.RABBIT, 2, 2, 6))
        spawnBuilder.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.SHEEP, 2, 2, 4))
        BiomeDefaultFeatures.caveSpawns(spawnBuilder)

        val featureBuilder = BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter)

        globalOverworldGeneration(featureBuilder)

        addFeature(featureBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.FOREST_ROCK)
        addFeature(featureBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LEAFLESS_OAK_TREE)

        BiomeDefaultFeatures.addFerns(featureBuilder)
        BiomeDefaultFeatures.addForestFlowers(featureBuilder)
        BiomeDefaultFeatures.addPlainGrass(featureBuilder)
        BiomeDefaultFeatures.addDefaultOres(featureBuilder)
        BiomeDefaultFeatures.addDefaultSoftDisks(featureBuilder)
        BiomeDefaultFeatures.addMeadowVegetation(featureBuilder)
        BiomeDefaultFeatures.addExtraEmeralds(featureBuilder)
        BiomeDefaultFeatures.addInfestedStone(featureBuilder)

        val musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW)

        return biome(true, 0.7f, 0.8f, 4159204, spawnBuilder, featureBuilder, musicSound)
    }

    private fun addFeature(
        builder: BiomeGenerationSettings.Builder,
        step: GenerationStep.Decoration,
        feature: ResourceKey<PlacedFeature>
    ) {
        builder.addFeature(step, feature)
    }
}