package dev.koding.headless.mixin;

import net.minecraft.client.gl.WindowFramebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WindowFramebuffer.class)
public class MixinWindowFramebuffer {

    @Inject(method = "initSize", at = @At("HEAD"), cancellable = true)
    private void initSize(int width, int height, CallbackInfo ci) {
        ci.cancel();
    }

}
