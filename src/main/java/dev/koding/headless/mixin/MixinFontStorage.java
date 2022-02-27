package dev.koding.headless.mixin;

import net.minecraft.client.font.Font;
import net.minecraft.client.font.FontStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FontStorage.class)
public class MixinFontStorage {

    @Inject(method = "setFonts", at = @At("HEAD"), cancellable = true)
    private void setFonts(List<Font> fonts, CallbackInfo ci) {
        ci.cancel();
    }

}
