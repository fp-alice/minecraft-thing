package net.fabricmc.example.client.mods.special;

import com.google.common.eventbus.Subscribe;
import net.fabricmc.example.client.mods.impl.Mod;
import net.fabricmc.example.events.impl.HudDrawEvent;

/**
 * This guy isn't actually a Mod object but it exists in the Mod context so I'm going to throw it in
 * this pkg
 */
public class ActiveModList extends SpecialMod {

	public ActiveModList() {
		super(true);
	}

	public void drawActiveMods() {
		int count = 0;
		if (this.getPlayer() != null) {
			for (Mod mod : this.getModManager().getEnabledMods()) {
				int x = 0;
				int y = 10 + (count * 10);
				this.getTextRenderer().drawWithShadow(mod.getIdentifier(), x, y, mod.getColor());
				count++;
			}
		}
	}

	@Subscribe
	public void onHudDrawEvent(HudDrawEvent event) {
		drawActiveMods();
	}
}
