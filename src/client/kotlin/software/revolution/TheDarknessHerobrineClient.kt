package software.revolution

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.loader.impl.FabricLoaderImpl
import software.revolution.data.ClientSettings
import software.revolution.entities.MobEntities
import software.revolution.entities.client.HerobrineModel
import software.revolution.entities.client.HerobrineRenderer

object TheDarknessHerobrineClient : ClientModInitializer {
    const val MOD_ID = "the-darkness-herobrine"

    val clientSettings = ClientSettings(FabricLoaderImpl.INSTANCE)

    override fun onInitializeClient() {
        EntityRendererRegistry.register(MobEntities.HEROBRINE) { HerobrineRenderer(it) }

        EntityModelLayerRegistry.registerModelLayer(
            HerobrineModel.HEROBRINE_MODEL_LAYER,
            HerobrineModel::getTexturedModelData
        )
    }
}