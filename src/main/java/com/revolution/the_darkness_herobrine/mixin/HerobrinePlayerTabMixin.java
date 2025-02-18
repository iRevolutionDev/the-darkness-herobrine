package com.revolution.the_darkness_herobrine.mixin;

import com.mojang.authlib.GameProfile;
import com.revolution.the_darkness_herobrine.config.Config;
import com.revolution.the_darkness_herobrine.event.gameplay.HerobrineFakeConnectionHandler;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(PlayerTabOverlay.class)
public class HerobrinePlayerTabMixin {

    @Unique
    private static final UUID HEROBRINE_UUID = UUID.fromString("f84c6a79-0a4e-45e0-879b-cd49ebd4c4e2");

    @Unique
    private static final String HEROBRINE_NAME = "Herobrine";

    @Inject(method = "getPlayerInfos", at = @At("RETURN"), cancellable = true)
    private void getPlayerInfos(CallbackInfoReturnable<List<PlayerInfo>> cir) {
        var config = Config.Companion.getINSTANCE();
        if (!config.getShowHerobrineInPlayerTabOverlay().get()) return;

        var connected = HerobrineFakeConnectionHandler.INSTANCE.getConnectedInServer();
        if (!connected) return;

        List<PlayerInfo> playerInfos = new ArrayList<>(cir.getReturnValue());
        var playerInfo = new PlayerInfo(new GameProfile(HEROBRINE_UUID, HEROBRINE_NAME), false);

        playerInfos.add(playerInfo);

        cir.setReturnValue(playerInfos);
    }
}
