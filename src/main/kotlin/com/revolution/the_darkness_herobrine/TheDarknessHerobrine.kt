package com.revolution.the_darkness_herobrine

import com.revolution.the_darkness_herobrine.config.Config
import com.revolution.the_darkness_herobrine.entity.ModEntities
import com.revolution.the_darkness_herobrine.entity.client.HerobrineRenderer
import com.revolution.the_darkness_herobrine.worldgen.biome.ModBiomePlacement
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.EntityRenderers
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

@Mod(TheDarknessHerobrine.ID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object TheDarknessHerobrine {
    const val ID = "the_darkness_herobrine"

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        ModEntities.ENTITY_TYPES.register(MOD_BUS)
        ModBiomePlacement.register()

        Config.register()

        runForDist(clientTarget = {
            MOD_BUS.addListener(::onClientSetup)
            Minecraft.getInstance()
        }, serverTarget = {
            MOD_BUS.addListener(::onServerSetup)
            "test"
        })
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")

        EntityRenderers.register(ModEntities.HEROBRINE_SPY.get(), ::HerobrineRenderer)
        EntityRenderers.register(ModEntities.HEROBRINE_STALKER.get(), ::HerobrineRenderer)
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        LOGGER.log(Level.INFO, "Hello! This is working!")
    }
}
