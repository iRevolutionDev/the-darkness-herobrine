package software.revolution.gui

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.option.GameOptionsScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.option.GameOptions
import net.minecraft.text.Text

class HerobrineSettingsScreen(parent: Screen, gameOptions: GameOptions) :
    GameOptionsScreen(parent, gameOptions, Text.translatable("ui.settings.title")) {

    override fun init() {
        addDrawableChild(
            ButtonWidget.builder(Text.translatable("ui.settings.back")) {
                client?.setScreen(parent)
            }.build()
        )
    }

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        this.renderBackground(context)

        context?.drawCenteredTextWithShadow(textRenderer, title, width / 2, 15, 16777215)

        super.render(context, mouseX, mouseY, delta)
    }
}