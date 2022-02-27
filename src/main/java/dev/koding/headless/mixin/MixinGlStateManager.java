package dev.koding.headless.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.IntBuffer;

@Mixin(GlStateManager.class)
public class MixinGlStateManager {

    @Inject(method = "_texParameter(III)V", at = @At("HEAD"), cancellable = true)
    private static void texParameter(int target, int pname, int param, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_texParameter(IIF)V", at = @At("HEAD"), cancellable = true)
    private static void texParameter(int target, int pname, float param, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_texImage2D", at = @At("HEAD"), cancellable = true)
    private static void texImage2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, IntBuffer pixels, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_activeTexture", at = @At("HEAD"), cancellable = true)
    private static void activeTexture(int texture, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_pixelStore", at = @At("HEAD"), cancellable = true)
    private static void pixelStore(int pname, int param, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_texSubImage2D", at = @At("HEAD"), cancellable = true)
    private static void texSubImage2D(int target, int level, int offsetX, int offsetY, int width, int height, int format, int type, long pixels, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_glGenBuffers", at = @At("HEAD"), cancellable = true, remap = false)
    private static void glGenBuffers(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

    @Inject(method = "_getInteger", at = @At("HEAD"), cancellable = true)
    private static void getInteger(int pname, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

    @Inject(method = "_getTexLevelParameter", at = @At("HEAD"), cancellable = true)
    private static void getTexLevelParameter(int target, int level, int pname, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

    @Inject(method = "glCreateProgram", at = @At("HEAD"), cancellable = true, remap = false)
    private static void glCreateProgram(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(1);
    }

    @Inject(method = "_glBindAttribLocation", at = @At("HEAD"), cancellable = true)
    private static void glBindAttribLocation(int program, int index, CharSequence name, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "glAttachShader", at = @At("HEAD"), cancellable = true)
    private static void glAttachShader(int program, int shader, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "glLinkProgram", at = @At("HEAD"), cancellable = true)
    private static void glLinkProgram(int program, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "glGetProgrami", at = @At("HEAD"), cancellable = true)
    private static void glGetProgrami(int program, int pname, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(1);
    }

    @Inject(method = "_glGetUniformLocation", at = @At("HEAD"), cancellable = true)
    private static void glGetUniformLocation(int program, CharSequence name, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(1);
    }

    @Inject(method = "_glBindBuffer", at = @At("HEAD"), cancellable = true)
    private static void glBindBuffer(int target, int buffer, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_glBindVertexArray", at = @At("HEAD"), cancellable = true)
    private static void glBindVertexArray(int array, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "_glGenVertexArrays", at = @At("HEAD"), cancellable = true, remap = false)
    private static void glGenVertexArrays(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }

}
