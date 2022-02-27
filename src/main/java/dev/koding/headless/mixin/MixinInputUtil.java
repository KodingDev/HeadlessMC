package dev.koding.headless.mixin;

import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InputUtil.class)
public class MixinInputUtil {

    @Inject(method = "isKeyPressed", at = @At("HEAD"), cancellable = true)
    private static void onIsKeyPressed(long handle, int code, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = "setCursorParameters", at = @At("HEAD"), cancellable = true)
    private static void onSetCursorParameters(long handler, int inputModeValue, double x, double y, CallbackInfo ci) {
        ci.cancel();
    }

}
