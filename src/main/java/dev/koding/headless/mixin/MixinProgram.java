package dev.koding.headless.mixin;

import net.minecraft.client.gl.GLImportProcessor;
import net.minecraft.client.gl.Program;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;

@Mixin(Program.class)
public class MixinProgram {

    @Inject(method = "loadProgram", at = @At("HEAD"), cancellable = true)
    private static void loadProgram(Program.Type type, String name, InputStream stream, String domain, GLImportProcessor loader, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

}
