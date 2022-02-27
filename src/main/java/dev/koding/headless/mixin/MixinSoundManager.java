package dev.koding.headless.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Mixin(SoundManager.class)
public class MixinSoundManager {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(boolean paused, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"), cancellable = true)
    public void play(SoundInstance sound, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;I)V", at = @At("HEAD"), cancellable = true)
    public void play(SoundInstance sound, int delay, CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/client/sound/SoundManager$SoundList;", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;getAllNamespaces()Ljava/util/Set;"))
    public Set<String> prepare(ResourceManager instance) {
        return new HashSet<>();
    }

    @Redirect(method = "apply(Lnet/minecraft/client/sound/SoundManager$SoundList;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundSystem;reloadSounds()V"))
    public void apply(SoundSystem instance) {
    }

}
