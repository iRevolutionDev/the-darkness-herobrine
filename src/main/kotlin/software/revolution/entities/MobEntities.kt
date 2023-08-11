package software.revolution.entities

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import software.revolution.TheDarknessHerobrine
import software.revolution.entities.custom.HerobrineEntity

object MobEntities {
    val HEROBRINE: EntityType<HerobrineEntity> = Registry.register(
        Registries.ENTITY_TYPE, Identifier(TheDarknessHerobrine.MOD_ID, "herobrine"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ::HerobrineEntity)
            .dimensions(EntityDimensions(0.6f, 1.95f, true))
            .build()
    )
}