package com.revolution.the_darkness_herobrine.entity.client

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.client.renderer.entity.state.PlayerRenderState
import net.minecraft.resources.ResourceLocation

class HerobrineRenderer(context: EntityRendererProvider.Context) : MobRenderer<HerobrineEntity, PlayerRenderState, HerobrineModel>(context, HerobrineModel(context.bakeLayer(HerobrineModel.HEROBRINE_MODEL_LAYER)), 0.5f) {
    override fun getTextureLocation(p0: PlayerRenderState): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, "textures/entity/herobrine.png")
    }

    override fun createRenderState(): PlayerRenderState {
        return PlayerRenderState()
    }
}