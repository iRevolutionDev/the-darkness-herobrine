package com.revolution.the_darkness_herobrine.event.core

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.entity.ModEntities
import com.revolution.the_darkness_herobrine.entity.client.HerobrineModel
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineSpy
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineStalker
import net.minecraft.world.entity.SpawnPlacementTypes
import net.minecraft.world.level.levelgen.Heightmap
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent

@EventBusSubscriber(modid = TheDarknessHerobrine.ID, bus = EventBusSubscriber.Bus.MOD)
object ModEventBusEvents {
    @SubscribeEvent
    fun registerLayers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(HerobrineModel.HEROBRINE_MODEL_LAYER, HerobrineModel::getTexturedModelData)
    }

    @SubscribeEvent
    fun registerAttributes(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.HEROBRINE_SPY.get(), HerobrineSpy.createAttributes().build())
        event.put(ModEntities.HEROBRINE_STALKER.get(), HerobrineStalker.createAttributes().build())
    }

    @SubscribeEvent
    fun registerSpawnPlacements(event: RegisterSpawnPlacementsEvent) {
        event.register(ModEntities.HEROBRINE_SPY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, { _, level, _, _, _ ->
            HerobrineSpy.canSpawn(level)
        }, RegisterSpawnPlacementsEvent.Operation.OR)

        event.register(ModEntities.HEROBRINE_STALKER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, { _, level, _, _, _ ->
            HerobrineStalker.canSpawn(level)
        }, RegisterSpawnPlacementsEvent.Operation.OR)
    }
}