package software.revolution.gui

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

class HerobrineSettingsScreen : Screen(Text.of("Herobrine Settings")) {

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        context?.fillGradient(0, 0, width, height, -1072689136, -804253680)
    }
}