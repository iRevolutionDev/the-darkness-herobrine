package software.revolution.data

import net.minecraft.server.MinecraftServer

class WorldSettings(server: MinecraftServer) : ServerDataSource(server, "world") {
    var canCrash: Boolean
        get() = read("canCrash", false)
        set(value) = add("canCrash", value)
}