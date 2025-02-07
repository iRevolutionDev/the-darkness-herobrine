package com.revolution.the_darkness_herobrine.entity.custom

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

class HerobrineEntity(type: EntityType<out HerobrineEntity>, level: Level) : Monster(type, level) {
    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_SPEED, 4.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }

    override fun registerGoals() {
        super.registerGoals()

        this.targetSelector.addGoal(1, LookAtPlayerGoal(this, Player::class.java, 12f))
    }
}