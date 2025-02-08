package com.revolution.the_darkness_herobrine.entity.client

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import net.minecraft.client.model.PlayerModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.resources.ResourceLocation
import org.jetbrains.annotations.Contract

class HerobrineModel(modelPart: ModelPart) : PlayerModel(modelPart, false) {
    companion object {
        val HEROBRINE_MODEL_LAYER = ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TheDarknessHerobrine.ID, "herobrine"), "main")

        @Contract("_ -> new")
        fun getTexturedModelData(): LayerDefinition {
            return LayerDefinition.create(createMesh(CubeDeformation(0.0f), false), 64, 64)
        }
    }
}