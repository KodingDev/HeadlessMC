package dev.koding.headless.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = {"com.mojang.blaze3d.platform.GlStateManager$CapabilityTracker"})
public class MixinCapabilityTracker {

    @Inject(method = "setState", at = @At("HEAD"), cancellable = true)
    private void setState(boolean state, CallbackInfo ci) {
        ci.cancel();
    }

}
