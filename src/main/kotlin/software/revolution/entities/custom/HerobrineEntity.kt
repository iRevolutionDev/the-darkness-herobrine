package software.revolution.entities.custom

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import software.revolution.TheDarknessHerobrine

class HerobrineEntity(entityType: EntityType<HerobrineEntity>, world: World) : HostileEntity(entityType, world) {

    companion object {
        fun setAttributes(): DefaultAttributeContainer.Builder? {
            return createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
        }
    }

    private var lifeTime = 0

    init {
        lifeTime = getRandom().nextBetween(100, 1000)

        TheDarknessHerobrine.logger.info("Herobrine spawned at $lifeTime")
    }


    override fun initGoals() {
        goalSelector.add(1, SwimGoal(this))
        goalSelector.add(2, LookAtEntityGoal(this, PlayerEntity::class.java, 12f))
        goalSelector.add(3, ActiveTargetGoal(this, PlayerEntity::class.java, false))

        super.initGoals()
    }

    override fun canTakeDamage(): Boolean {
        return false
    }

    override fun onAttacking(target: Entity?) {
        super.onAttacking(target)
    }

    override fun mobTick() {
        lifeTime--

        if (lifeTime <= 0) {
            TheDarknessHerobrine.logger.info("Herobrine removed at $lifeTime")
            remove(RemovalReason.DISCARDED)
        }

        super.mobTick()
    }
}