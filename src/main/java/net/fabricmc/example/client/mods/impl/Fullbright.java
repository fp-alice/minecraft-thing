package net.fabricmc.example.client.mods.impl;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class Fullbright extends Mod {

	public Fullbright() {
		super("fullbright", GLFW.GLFW_KEY_B, 0xFFFF00);
	}

	@Override
	void onEnable() {
		MinecraftClient.getInstance().options.gamma = 10f;
	}

	@Override
	void onDisable() {
		MinecraftClient.getInstance().options.gamma = 1f;
	}
}
