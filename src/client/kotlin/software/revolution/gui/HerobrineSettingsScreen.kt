package software.revolution.gui

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.option.GameOptionsScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.option.GameOptions
import net.minecraft.client.option.SimpleOption
import net.minecraft.text.Text
import software.revolution.TheDarknessHerobrine

class HerobrineSettingsScreen(parent: Screen, gameOptions: GameOptions) :
    GameOptionsScreen(parent, gameOptions, Text.translatable("ui.settings.title")) {

    override fun init() {
        val halfWidthMinusPadding = width / 2 - 155
        val halfWidthPlusPadding = halfWidthMinusPadding + 160
        val halfHeightMinusPadding = height / 6 - 12

        addDrawableChild(
            SimpleOption.ofBoolean(
                "ui.settings.options.crash",
                SimpleOption.emptyTooltip(),
                { _, _ ->
                    if (TheDarknessHerobrine.worldSettings.canCrash) Text.of("Yes")
                    else Text.of("No")
                },
                false,
                {
                    TheDarknessHerobrine.worldSettings.canCrash = it
                }
            ).createWidget(
                gameOptions,
                halfWidthMinusPadding,
                halfHeightMinusPadding,
                310
            )
        )

        addDrawableChild(
            ButtonWidget.builder(Text.translatable("ui.settings.back")) {
                client?.setScreen(parent)
            }.dimensions(halfWidthMinusPadding, height - 25, 150, 20).build()
        )

        addDrawableChild(
            ButtonWidget.builder(Text.translatable("ui.settings.reset")) {

            }.dimensions(halfWidthPlusPadding, height - 25, 150, 20).build()
        )
    }

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        this.renderBackground(context)

        context?.drawCenteredTextWithShadow(textRenderer, title, width / 2, 15, 16777215)

        super.render(context, mouseX, mouseY, delta)
    }
}