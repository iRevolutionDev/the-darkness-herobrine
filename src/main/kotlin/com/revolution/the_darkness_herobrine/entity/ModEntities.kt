package com.revolution.the_darkness_herobrine.entity

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineEntity
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineSpy
import com.revolution.the_darkness_herobrine.entity.custom.HerobrineStalker
import net.minecraft.world.entity.MobCategory
import net.neoforged.neoforge.registries.DeferredRegister

object ModEntities {
    val ENTITY_TYPES = DeferredRegister.createEntities(TheDarknessHerobrine.ID)

    val HEROBRINE_SPY = ENTITY_TYPES.registerEntityType("herobrine_spy", ::HerobrineSpy, MobCategory.MONSTER)
    val HEROBRINE_STALKER = ENTITY_TYPES.registerEntityType("herobrine_stalker", ::HerobrineStalker, MobCategory.MONSTER)
}