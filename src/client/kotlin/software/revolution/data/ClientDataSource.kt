package software.revolution.data

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.fabricmc.loader.impl.FabricLoaderImpl
import kotlin.io.path.*

open class ClientDataSource(fabric: FabricLoaderImpl, fileName: String) : DataSource {

    private val file = fabric.configDir.resolve("config/herobrine").resolve("$fileName.herobrine")
    private var json: JsonObject? = null

    override fun save() {
        file.writeText(json.toString())
    }

    val settings: JsonObject
        get() = json ?: throw IllegalStateException("Settings not loaded")

    fun <T> add(key: String, value: T, save: Boolean = true) {
        when (value) {
            is String -> settings.addProperty(key, value)
            is Number -> settings.addProperty(key, value)
            is Boolean -> settings.addProperty(key, value)
            is Char -> settings.addProperty(key, value)
            else -> throw IllegalArgumentException("Unsupported type")
        }

        if (save) save()
    }

    inline fun <reified T> read(key: String, default: T): T {
        val value = settings[key] ?: return default

        return when (default) {
            is String -> value.asString
            is Number -> {
                try {
                    value.asInt
                } catch (e: NumberFormatException) {
                    value.asDouble
                }
            }

            is Boolean -> value.asBoolean
            else -> throw IllegalArgumentException("Unsupported type")
        } as T
    }

    init {
        if (!file.parent.exists()) file.parent.createDirectories()
        if (!file.exists()) file.createFile()
        if (file.readText().isEmpty()) file.writeText("{}")

        json = JsonParser.parseString(file.readText()).asJsonObject
    }
}