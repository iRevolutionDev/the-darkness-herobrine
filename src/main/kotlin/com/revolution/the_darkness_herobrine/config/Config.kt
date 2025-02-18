package com.revolution.the_darkness_herobrine.config

import net.neoforged.fml.ModLoadingContext
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.ModConfigSpec
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue
import org.apache.commons.lang3.tuple.Pair

class Config(builder: ModConfigSpec.Builder) {
    companion object {
        private val pair: Pair<Config, ModConfigSpec> = ModConfigSpec.Builder().configure(::Config)

        val CONFIG: Config = pair.left
        val CONFIG_SPEC: ModConfigSpec = pair.right

        @Volatile
        var isConfigLoaded: Boolean = false
            internal set

        fun register() {
            val context = ModLoadingContext.get()
            val container = context.activeContainer

            container.registerConfig(ModConfig.Type.SERVER, CONFIG_SPEC, "the_darkness_herobrine.toml")
        }
    }

    private var _canSendJoinMessages: BooleanValue
    private var _showHerobrineInPlayerTabOverlay: BooleanValue

    var canSendJoinMessages: Boolean
        get() = _canSendJoinMessages.get()
        set(value) = _canSendJoinMessages.set(value)

    var showHerobrineInPlayerTabOverlay: Boolean
        get() = _showHerobrineInPlayerTabOverlay.get()
        set(value) = _showHerobrineInPlayerTabOverlay.set(value)

    init {
        builder.comment("General settings").push("general")

        _canSendJoinMessages = builder
            .comment("Herobrine will send join or leave messages in chat")
            .define("can_send_join_messages", true)

        _showHerobrineInPlayerTabOverlay = builder
            .comment("Show Herobrine in the player tab overlay")
            .define("show_herobrine_in_player_tab_overlay", true)

        builder.pop()
    }
}