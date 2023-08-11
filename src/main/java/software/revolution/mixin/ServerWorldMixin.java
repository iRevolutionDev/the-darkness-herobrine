package software.revolution.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.revolution.entities.MobEntities;

import java.util.Random;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

    @Shadow @NotNull public abstract MinecraftServer getServer();

    @Shadow @Nullable public abstract ServerPlayerEntity getRandomAlivePlayer();

    @Unique
    private int spawnHerobrineTimer = 0;

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo info) {
        spawnHerobrineTimer--;

        if (spawnHerobrineTimer <= 0) {
            ServerPlayerEntity player = getRandomAlivePlayer();

            if (player == null) return;

            // Spawn Herobrine in front of player
            getServer().getWorld(player.getEntityWorld().getRegistryKey())
                    .spawnEntity(MobEntities.INSTANCE.getHEROBRINE()
                            .spawn(getServer().getWorld(player.getEntityWorld().getRegistryKey()), BlockPos.ofFloored(player.getRotationVector().multiply(5))
                                    , SpawnReason.TRIGGERED));

            spawnHerobrineTimer = new Random().nextInt(100);
        }
    }
}