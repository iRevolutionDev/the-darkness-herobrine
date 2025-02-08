package com.revolution.the_darkness_herobrine.entity.client

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineEntity
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineState
import com.revolution.the_darkness_herobrine.entity.custom.getTextureLocation
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class HerobrineRenderer(context: EntityRendererProvider.Context) : MobRenderer<HerobrineEntity, HerobrineState, HerobrineModel>(context, HerobrineModel(context.bakeLayer(HerobrineModel.HEROBRINE_MODEL_LAYER)), 0.5f) {
    override fun getTextureLocation(state: HerobrineState): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, state.variant.getTextureLocation())
    }

    override fun extractRenderState(entity: HerobrineEntity, state: HerobrineState, speed: Float) {
        super.extractRenderState(entity, state, speed)

        state.variant = entity.variant
    }

    override fun createRenderState(): HerobrineState {
        return HerobrineState()
    }
}