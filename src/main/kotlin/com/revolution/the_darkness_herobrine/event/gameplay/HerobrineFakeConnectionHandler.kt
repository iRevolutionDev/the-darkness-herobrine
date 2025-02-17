package com.revolution.the_darkness_herobrine.event.gameplay

import com.revolution.the_darkness_herobrine.TheDarknessHerobrine
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.tick.ServerTickEvent
import net.neoforged.neoforge.server.ServerLifecycleHooks

@EventBusSubscriber(modid = TheDarknessHerobrine.ID)
object HerobrineFakeConnectionHandler {
    private const val MIN_INTERVAL = 6000
    private const val MAX_INTERVAL = 18000
    private var interval = getInterval()

    private val messages = listOf(
        Component
            .translatable("multiplayer.player.joined", "Herobrine")
            .withStyle {
                it.withColor(ChatFormatting.YELLOW)
            },
        Component
            .translatable("multiplayer.player.left", "Herobrine")
            .withStyle {
                it.withColor(ChatFormatting.YELLOW)
            }
    )

    @SubscribeEvent
    fun onServerTick(event: ServerTickEvent.Post) {
        interval--

        if (interval <= 0) {
            sendBroadcastMessage(messages.random())
            interval = getInterval()
        }
    }

    private fun getInterval(): Int {
        return (Math.random() * (MAX_INTERVAL - MIN_INTERVAL)).toInt() + MIN_INTERVAL
    }

    private fun sendBroadcastMessage(message: Component) {
        val server = ServerLifecycleHooks.getCurrentServer()
        server?.playerList?.players?.forEach { player ->
            player.sendSystemMessage(message)
        }
    }
}