package dev.koding.headless.mixin;

import net.minecraft.client.gl.Framebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Framebuffer.class)
public class MixinFramebuffer {

    @Inject(method = "clear", at = @At("HEAD"), cancellable = true)
    private void clear(boolean getError, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "resizeInternal", at = @At("HEAD"), cancellable = true)
    private void resizeInternal(int width, int height, boolean getError, CallbackInfo ci) {
        ci.cancel();
    }

}
