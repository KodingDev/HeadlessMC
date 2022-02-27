package dev.koding.headless.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.LongSupplier;

@Mixin(RenderSystem.class)
public class MixinRenderSystem {

    @Inject(method = "initBackendSystem", at = @At("HEAD"), remap = false, cancellable = true)
    private static void initBackendSystem(CallbackInfoReturnable<LongSupplier> cir) {
        cir.setReturnValue(System::nanoTime);
        cir.cancel();
    }

    @Inject(method = "initRenderer", at = @At("HEAD"), remap = false, cancellable = true)
    private static void initRenderer(int debugVerbosity, boolean debugSync, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "setupDefaultState", at = @At("HEAD"), remap = false, cancellable = true)
    private static void setupDefaultState(CallbackInfo ci) {
        ci.cancel();
    }

}
