package dev.koding.headless.mixin;

import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalNear;
import baritone.api.process.ICustomGoalProcess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Queue;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient extends ReentrantThreadExecutor<Runnable> {

    public MixinMinecraftClient(String string) {
        super(string);
    }

    @Shadow
    public abstract void tick();

    @Shadow
    @Final
    private Queue<Runnable> renderTaskQueue;

    @Shadow
    @Final
    private RenderTickCounter renderTickCounter;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(boolean tick, CallbackInfo ci) {
        Runnable runnable;
        while ((runnable = this.renderTaskQueue.poll()) != null) {
            runnable.run();
        }

        if (tick) {
            int i = this.renderTickCounter.beginRenderTick(Util.getMeasuringTimeMs());
            this.runTasks();
            for (int j = 0; j < Math.min(10, i); ++j) {
                this.tick();
            }
        }

        ci.cancel();
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ConnectScreen;connect(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;)V"))
    private void connect(Screen screen, MinecraftClient client, ServerAddress address, ServerInfo info) {
        ConnectScreen.connect(new TitleScreen(), client, address, new ServerInfo("Direct", address.getAddress() + ":" + address.getPort(), false));
    }

    // TODO: Remove these
    @Inject(method = "joinWorld", at = @At("RETURN"))
    private void joinWorld(ClientWorld world, CallbackInfo ci) {
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("schmovin");

            ICustomGoalProcess goal = BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess();
            goal.setGoal(new GoalNear(new BlockPos(184, 71, -25), 1));
            goal.path();
        }).start();
    }

    @Inject(method = "setScreen", at = @At("HEAD"))
    private void setScreen(Screen screen, CallbackInfo ci) {
        System.out.println("Changing screen to " + screen);
    }

}
