package com.revolution.the_darkness_herobrine.config

import net.neoforged.fml.ModLoadingContext
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.ModConfigSpec
import org.apache.commons.lang3.tuple.Pair

class Config private constructor(builder: ModConfigSpec.Builder) {
    var canSendJoinMessages: Boolean
    var showHerobrineInPlayerTabOverlay: Boolean

    init {
        builder.comment("General settings").push("general")

        canSendJoinMessages = builder
            .comment("Herobrine will send join or leave messages in chat")
            .define("can_send_join_messages", true)
            .get()

        showHerobrineInPlayerTabOverlay = builder
            .comment("Show Herobrine in the player tab overlay")
            .define("show_herobrine_in_player_tab_overlay", true)
            .get()

        builder.pop()
    }

    companion object {
        private val pair: Pair<Config, ModConfigSpec> = ModConfigSpec.Builder().configure(::Config)
        val INSTANCE: Config = pair.left
        val SPEC: ModConfigSpec = pair.right

        @Volatile
        var isLoaded: Boolean = false
            internal set

        fun register() {
            ModLoadingContext.get().apply {
                activeContainer.registerConfig(ModConfig.Type.SERVER, SPEC, "the_darkness_herobrine.toml")
            }
        }
    }
}