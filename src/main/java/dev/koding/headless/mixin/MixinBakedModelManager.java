package dev.koding.headless.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModelManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BakedModelManager.class)
public class MixinBakedModelManager {

    @Inject(method = "shouldRerender", at = @At("HEAD"), cancellable = true)
    private void shouldRerender(BlockState from, BlockState _to, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

}
