package software.revolution.mixin.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import software.revolution.gui.HerobrineSettingsScreen;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Shadow
    @Final
    private GameOptions settings;

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Shadow
    protected abstract ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier);

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 2), method = "init", locals = LocalCapture.CAPTURE_FAILHARD)
    private void addChild(CallbackInfo ci, GridWidget gridWidget, GridWidget.Adder adder) {
        adder.add(this.createButton(Text.translatable("ui.settings.button"), () -> new HerobrineSettingsScreen(this, this.settings)), 2,
                adder.copyPositioner().alignRight()
        );
    }
}
