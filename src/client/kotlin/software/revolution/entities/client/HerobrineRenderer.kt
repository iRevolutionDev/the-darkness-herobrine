package software.revolution.entities.client

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import software.revolution.TheDarknessHerobrineClient
import software.revolution.entities.custom.HerobrineEntity

class HerobrineRenderer(context: EntityRendererFactory.Context) : MobEntityRenderer<HerobrineEntity, HerobrineModel>(
    context, HerobrineModel(context.getPart(HerobrineModel.HEROBRINE_MODEL_LAYER)), 0.5f
) {
    override fun getTexture(entity: HerobrineEntity?): Identifier {
        return Identifier(TheDarknessHerobrineClient.MOD_ID, "textures/entity/herobrine.png")
    }
}