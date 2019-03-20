package net.fabricmc.example.client.mods.special;

import com.google.common.eventbus.Subscribe;
import net.fabricmc.example.events.impl.HudDrawEvent;
import net.minecraft.client.network.ClientPlayerEntity;

public class CoordinateOverlay extends SpecialMod {

	public CoordinateOverlay() {
		super(true);
	}

	public void drawCoordinates() {
		ClientPlayerEntity player = this.getPlayer();
		if (player != null) {
			String coords = String.format("(%.1f, %.1f, %.1f)", player.x, player.y, player.z);
			this.getTextRenderer().drawWithShadow(coords, 0, 0, 0xFFFFFF);
		}
	}

	@Subscribe
	public void onHudDrawEvent(HudDrawEvent event) {
		drawCoordinates();
	}
}
