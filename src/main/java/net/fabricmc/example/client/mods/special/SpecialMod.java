package net.fabricmc.example.client.mods.special;

import lombok.Getter;
import net.fabricmc.example.client.mods.ModManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;

public class SpecialMod {
	@Getter
	private boolean listener = false;

	public SpecialMod(boolean isListener) {
		listener = isListener;
	}

	public SpecialMod() {
		this(false);
	}

	public MinecraftClient getMinecraftClient() {
		return MinecraftClient.getInstance();
	}

	public ModManager getModManager() {
		return ModManager.getInstance();
	}

	public TextRenderer getTextRenderer() {
		return getMinecraftClient().textRenderer;
	}

	public ClientPlayerEntity getPlayer() {
		return getMinecraftClient().player;
	}

}
