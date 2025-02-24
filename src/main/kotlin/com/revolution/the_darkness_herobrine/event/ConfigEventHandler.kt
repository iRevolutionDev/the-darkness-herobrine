package com.revolution.the_darkness_herobrine.event

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.config.Config
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.event.config.ModConfigEvent

@EventBusSubscriber(modid = TheDarknessHerobrine.ID, bus = EventBusSubscriber.Bus.MOD)
object ConfigEventHandler {
    @SubscribeEvent
    fun onLoad(event: ModConfigEvent.Loading) {
        if (event.config.spec == Config.SPEC) {
            Config.isLoaded = true
        }
    }

    @SubscribeEvent
    fun onReload(event: ModConfigEvent.Unloading) {
        if (event.config.spec == Config.SPEC) {
            Config.isLoaded = false
        }
    }

    @SubscribeEvent
    fun onReload(event: ModConfigEvent.Reloading) {
        if (event.config.spec == Config.SPEC) {
            Config.isLoaded = true
        }
    }
}