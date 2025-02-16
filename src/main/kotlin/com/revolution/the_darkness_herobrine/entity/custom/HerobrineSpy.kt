package com.revolution.the_darkness_herobrine.entity.custom

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.Difficulty
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.npc.Villager
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class HerobrineSpy(type: EntityType<out HerobrineSpy>, level: Level) : HerobrineEntity(type, level) {
   private var lifetime: Int = 5000;

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_SPEED, 4.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }

        fun canSpawn(
            type: EntityType<out HerobrineSpy>,
            level: ServerLevelAccessor,
            reason: EntitySpawnReason,
            location: BlockPos,
            random: RandomSource
        ): Boolean {
            return level.difficulty != Difficulty.PEACEFUL
        }
    }

    override fun registerGoals() {
        super.registerGoals()

        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(1, LookAtPlayerGoal(this, Player::class.java, 1024f))
        goalSelector.addGoal(2, LookAtPlayerGoal(this, Villager::class.java, 1024f))
        goalSelector.addGoal(3, AvoidEntityGoal(this, Player::class.java, 32.0f, 0.7, 5.0))
    }

    override fun tick() {
        if (lifetime < 1) {
            remove(RemovalReason.DISCARDED)
        }
        lifetime--

        val box = boundingBox.inflate(8.0)
        val players = level().getEntitiesOfClass(LivingEntity::class.java, box) { it is LivingEntity }

        for (player in players) {
            if (player !is Player || player.isCreative) continue

            remove(RemovalReason.DISCARDED)
        }

        super.tick()
    }
}