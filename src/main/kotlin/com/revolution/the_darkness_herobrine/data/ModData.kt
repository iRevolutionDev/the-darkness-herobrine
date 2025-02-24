package com.revolution.the_darkness_herobrine.data

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.data.providers.ModBiomeTagsProvider
import com.revolution.the_darkness_herobrine.worldgen.WorldgenData
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber(modid = TheDarknessHerobrine.ID, bus = EventBusSubscriber.Bus.MOD)
object ModData {

    @SubscribeEvent
    fun onLoadData(event: GatherDataEvent.Client) {
        val provider = WorldgenData(event.generator.packOutput, event.lookupProvider)

        event.generator.addProvider(true, provider)
        event.generator.addProvider(true, ModBiomeTagsProvider(event.generator.packOutput, event.lookupProvider))
    }
}