package software.revolution

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.revolution.entities.MobEntities
import software.revolution.entities.custom.HerobrineEntity

object TheDarknessHerobrine : ModInitializer {
    const val MOD_ID = "the-darkness-herobrine"
    private val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_ID")

        FabricDefaultAttributeRegistry.register(
            MobEntities.HEROBRINE,
            HerobrineEntity.setAttributes()
        )
    }
}