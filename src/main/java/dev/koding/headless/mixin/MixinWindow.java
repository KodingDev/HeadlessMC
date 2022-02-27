package dev.koding.headless.mixin;

import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GLCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;

@Mixin(Window.class)
public class MixinWindow {

    // Constructor
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwGetPrimaryMonitor()J"))
    private long glfwGetPrimaryMonitor() {
        return 0L;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/MonitorTracker;getMonitor(J)Lnet/minecraft/client/util/Monitor;"))
    private Monitor getMonitor(MonitorTracker instance, long pointer) {
        return null;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwDefaultWindowHints()V"))
    private void glfwDefaultWindowHints() {
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwWindowHint(II)V"))
    private void glfwWindowHint(int hint, int value) {
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"))
    private long glfwCreateWindow(int titleEncoded, int width, CharSequence height, long title, long monitor) {
        return 0;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwGetWindowPos(J[I[I)V"))
    private void glfwGetWindowPos(long window, int[] x, int[] y) {
        x[0] = 0;
        y[0] = 0;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwMakeContextCurrent(J)V"))
    private void glfwMakeContextCurrent(long window) {
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL;createCapabilities()Lorg/lwjgl/opengl/GLCapabilities;"))
    private GLCapabilities createCapabilities() {
        return null;
    }

    @Inject(method = "updateWindowRegion", at = @At("HEAD"), cancellable = true)
    private void updateWindowRegion(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "updateFramebufferSize", at = @At("HEAD"), cancellable = true)
    private void updateFramebufferSize(CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetFramebufferSizeCallback(JLorg/lwjgl/glfw/GLFWFramebufferSizeCallbackI;)Lorg/lwjgl/glfw/GLFWFramebufferSizeCallback;"))
    private GLFWFramebufferSizeCallback glfwSetFramebufferSizeCallback(long window, GLFWFramebufferSizeCallbackI cbfun) {
        return null;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowPosCallback(JLorg/lwjgl/glfw/GLFWWindowPosCallbackI;)Lorg/lwjgl/glfw/GLFWWindowPosCallback;"))
    private GLFWWindowPosCallback glfwSetWindowPosCallback(long window, GLFWWindowPosCallbackI cbfun) {
        return null;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowSizeCallback(JLorg/lwjgl/glfw/GLFWWindowSizeCallbackI;)Lorg/lwjgl/glfw/GLFWWindowSizeCallback;"))
    private GLFWWindowSizeCallback glfwSetWindowSizeCallback(long window, GLFWWindowSizeCallbackI cbfun) {
        return null;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowFocusCallback(JLorg/lwjgl/glfw/GLFWWindowFocusCallbackI;)Lorg/lwjgl/glfw/GLFWWindowFocusCallback;"))
    private GLFWWindowFocusCallback glfwSetWindowFocusCallback(long window, GLFWWindowFocusCallbackI cbfun) {
        return null;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetCursorEnterCallback(JLorg/lwjgl/glfw/GLFWCursorEnterCallbackI;)Lorg/lwjgl/glfw/GLFWCursorEnterCallback;"))
    private GLFWCursorEnterCallback glfwSetCursorEnterCallback(long window, GLFWCursorEnterCallbackI cbfun) {
        return null;
    }

    // Other shit
    @Inject(method = "setIcon", at = @At("HEAD"), cancellable = true)
    private void setIcon(InputStream icon16, InputStream icon32, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "setTitle", at = @At("HEAD"), cancellable = true)
    private void setTitle(String title, CallbackInfo ci) {
        ci.cancel();
    }

}
