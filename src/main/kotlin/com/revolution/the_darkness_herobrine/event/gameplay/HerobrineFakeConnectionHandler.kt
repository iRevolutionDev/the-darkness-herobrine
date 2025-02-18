package com.revolution.the_darkness_herobrine.event.gameplay

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import com.revolution.the_darkness_herobrine.config.Config
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.event.tick.ServerTickEvent
import net.neoforged.neoforge.server.ServerLifecycleHooks

@EventBusSubscriber(modid = TheDarknessHerobrine.ID)
object HerobrineFakeConnectionHandler {
    var connectedInServer: Boolean = false

    private const val MIN_INTERVAL = 6000
    private const val MAX_INTERVAL = 18000

    private const val HEROBRINE_NAME = "Herobrine"

    private var interval = getInterval()

    private val messages = listOf(
        Component
            .translatable("multiplayer.player.joined", HEROBRINE_NAME)
            .withStyle {
                it.withColor(ChatFormatting.YELLOW)
            },
        Component
            .translatable("multiplayer.player.left", HEROBRINE_NAME)
            .withStyle {
                it.withColor(ChatFormatting.YELLOW)
            },
    )

    @SubscribeEvent
    fun onServerTick(event: ServerTickEvent.Post) {
        if (!Config.INSTANCE.canSendJoinMessages) return

        interval--

        if (interval > 0) return

        if (messages.isNotEmpty()) {
            sendBroadcastMessage(messages.random())
        }

        interval = getInterval()
    }

    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        if (Config.isLoaded && !Config.INSTANCE.showHerobrineInPlayerTabOverlay) return

        interval--

        if (interval > 0) return

        // This is a fake connection of Herobrine in the server
        connectedInServer = !connectedInServer

        interval = getInterval()
    }

    private fun getInterval(): Int {
        return (Math.random() * (MAX_INTERVAL - MIN_INTERVAL)).toInt() + MIN_INTERVAL
    }

    private fun sendBroadcastMessage(message: Component) {
        val server = ServerLifecycleHooks.getCurrentServer()
        server?.playerList?.broadcastSystemMessage(message, false)
    }
}