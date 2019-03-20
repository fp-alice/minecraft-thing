package net.fabricmc.example.client.mods.impl;

import lombok.Getter;
import net.fabricmc.example.client.KeybindManager;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;

import java.util.Random;

public abstract class Mod {

	@Getter
	int color;
	@Getter
	private String identifier;
	@Getter
	private boolean enabled = false;
	@Getter
	private FabricKeyBinding keyBinding;

	@Getter
	private boolean listener = false;

	public Mod(String identifier, int key, int color, boolean isListener) {
		this.identifier = identifier;
		if (color != -1) {
			this.color = color;
		} else {
			// Random color if color is -1
			Random r = new Random();
			this.color = r.nextInt(0xFFFFFF) + 1;
		}
		if (key != -1) {
			this.keyBinding = KeybindManager.getInstance().addKeybind(identifier, key);
		}
		this.listener = isListener;
	}

	public Mod(String identifier, int key, int color) {
		this(identifier, key, color, false);
	}

	abstract void onEnable();

	abstract void onDisable();

	public void toggle() {
		this.enabled = !this.enabled;

		if (this.enabled) {
			onEnable();
		} else {
			onDisable();
		}
	}
}
