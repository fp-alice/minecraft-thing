package net.fabricmc.example.client.mods.special;

import com.google.common.eventbus.Subscribe;
import net.fabricmc.example.events.impl.HudDrawEvent;

public class KeypressListener extends SpecialMod {

	public KeypressListener() {
		super(true);
	}

	public void checkKeypresses() {
		if (this.getPlayer() != null) {
			this.getModManager().getMods().stream()
					.filter(mod -> mod.getKeyBinding() != null)
					.forEach(
							mod -> {
								if (mod.getKeyBinding().wasPressed()) {
									mod.toggle();
									System.out.println("KEY WAS PRESSED FOR MOD " + mod.getIdentifier());
								}
							});
		}
	}

	@Subscribe
	public void onHudDrawEvent(HudDrawEvent event) {
		checkKeypresses();
	}
}
