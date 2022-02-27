package dev.koding.headless.mixin;

import com.mojang.blaze3d.platform.TextureUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TextureUtil.class)
public class MixinTextureUtil {

    /**
     * @author balls
     */
    @Overwrite(remap = false)
    public static int generateTextureId() {
        return 0;
    }
}
