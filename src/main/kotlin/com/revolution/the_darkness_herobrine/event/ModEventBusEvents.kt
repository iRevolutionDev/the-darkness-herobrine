package com.revolution.the_darkness_herobrine.event

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.entity.ModEntities
import com.revolution.the_darkness_herobrine.entity.client.HerobrineModel
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineSpy
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineStalker
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent

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
}