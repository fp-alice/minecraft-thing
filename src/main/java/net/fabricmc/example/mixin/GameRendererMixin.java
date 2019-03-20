package net.fabricmc.example.mixin;


import net.fabricmc.example.events.EventManager;
import net.fabricmc.example.events.impl.WorldRenderEvent;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

	@Inject(at = @At(value = "INVOKE_STRING", args = "ldc=hand", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V"), method = "renderCenter")
	private void renderAfterWorld(float partialTicks, long finishTimeNano, CallbackInfo info) {
		EventManager.getInstance().post(new WorldRenderEvent(partialTicks));
	}
}
