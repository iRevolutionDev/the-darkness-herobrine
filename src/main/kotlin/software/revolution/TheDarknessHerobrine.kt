package software.revolution

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TheDarknessHerobrine : ModInitializer {
    const val MOD_ID = "the-darkness-herobrine"
    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_ID")
    }
}