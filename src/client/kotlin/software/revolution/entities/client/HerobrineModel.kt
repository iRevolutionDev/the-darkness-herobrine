package software.revolution.entities.client

import net.minecraft.client.model.Dilation
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.util.Identifier
import org.jetbrains.annotations.Contract
import software.revolution.TheDarknessHerobrine
import software.revolution.entities.custom.HerobrineEntity

class HerobrineModel(modelPart: ModelPart) : PlayerEntityModel<HerobrineEntity>(modelPart, false) {
    companion object {
        val HEROBRINE_MODEL_LAYER = EntityModelLayer(Identifier(TheDarknessHerobrine.MOD_ID, "herobrine"), "main")

        @Contract("_ -> new")
        fun getTexturedModelData(): TexturedModelData {
            return TexturedModelData.of(getTexturedModelData(Dilation.NONE, false), 64, 64)
        }
    }
}