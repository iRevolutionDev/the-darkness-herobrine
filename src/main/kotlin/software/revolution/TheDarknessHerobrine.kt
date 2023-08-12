package software.revolution

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.revolution.data.WorldSettings
import software.revolution.entities.MobEntities
import software.revolution.entities.custom.HerobrineEntity

object TheDarknessHerobrine : ModInitializer {
    const val MOD_ID = "the-darkness-herobrine"
    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    lateinit var worldSettings: WorldSettings

    override fun onInitialize() {
        logger.info("Initializing $MOD_ID")

        registerAttributes()
        registerEvents()
    }

    private fun registerAttributes() {
        FabricDefaultAttributeRegistry.register(
            MobEntities.HEROBRINE,
            HerobrineEntity.setAttributes()
        )
    }

    private fun registerEvents() {
        ServerLifecycleEvents.SERVER_STARTED.register {
            worldSettings = WorldSettings(it)
        }

        ServerLifecycleEvents.SERVER_STOPPING.register {
            worldSettings.save()
        }
    }
}