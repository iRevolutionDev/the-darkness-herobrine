package software.revolution.entities.custom

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import software.revolution.entities.MobEntities

class HerobrineEntity(entityType: EntityType<HerobrineEntity>, world: World) : PassiveEntity(entityType, world) {

    companion object {
        fun setAttributes(): DefaultAttributeContainer.Builder? {
            return createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
        }
    }


    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))

        super.initGoals()
    }

    override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? {
        return MobEntities.HEROBRINE.create(world)
    }
}