package dev.koding.headless.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModels.class)
public class MixinBlockModels {

    @Inject(method = "getModelParticleSprite", at = @At("HEAD"), cancellable = true)
    private void getModelParticleSprite(BlockState state, CallbackInfoReturnable<Sprite> cir) {
        cir.setReturnValue(null);
    }

}
