package com.revolution.the_darkness_herobrine.entity

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineEntity
import net.minecraft.world.entity.MobCategory
import net.neoforged.neoforge.registries.DeferredRegister

object ModEntities {
    val ENTITY_TYPES = DeferredRegister.createEntities(TheDarknessHerobrine.ID);

     val HEROBRINE = ENTITY_TYPES.registerEntityType("herobrine", ::HerobrineEntity, MobCategory.MONSTER)
}