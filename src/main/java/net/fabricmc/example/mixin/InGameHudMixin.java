package net.fabricmc.example.mixin;

import net.fabricmc.example.events.EventManager;
import net.fabricmc.example.events.impl.HudDrawEvent;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Inject(at = @At("RETURN"), method = "draw(F)V")
	private void draw(CallbackInfo info) {
		EventManager.getInstance().post(new HudDrawEvent());
	}
}
