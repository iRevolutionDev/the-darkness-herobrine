package com.revolution.the_darkness_herobrine.entity.custom

import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.RandomSource
import net.minecraft.world.Difficulty
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

open class HerobrineEntity(type: EntityType<out HerobrineEntity>, level: Level) : Monster(type, level) {
    companion object {
        val VARIANT: EntityDataAccessor<Int> = SynchedEntityData.defineId(HerobrineEntity::class.java, EntityDataSerializers.INT)

        fun createAttributes(): AttributeSupplier.Builder {
            return createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_SPEED, 4.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }

        fun canSpawn(
            type: EntityType<HerobrineEntity>?,
            level: ServerLevelAccessor,
            reason: EntitySpawnReason,
            location: BlockPos,
            random: RandomSource
        ): Boolean {
            return level.difficulty != Difficulty.PEACEFUL
        }
    }

    private val typeVariant: Int
        get() = this.entityData.get(VARIANT)

    var variant: HerobrineVariant
        get() = HerobrineVariant.byId(this.typeVariant)
        set(value) {
            this.entityData.set(VARIANT, value.id)
        }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)

        builder.define(VARIANT, 0)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("Variant", this.typeVariant)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.variant = HerobrineVariant.byId(compound.getInt("Variant"))
    }

    override fun finalizeSpawn(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        spawnGroupData: SpawnGroupData?
    ): SpawnGroupData? {
        this.variant = Util.getRandom(HerobrineVariant.entries.toTypedArray(), random)
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData)
    }
}