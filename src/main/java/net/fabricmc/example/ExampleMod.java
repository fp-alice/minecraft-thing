package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.client.mods.ModManager;

public class ExampleMod implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
//    EventManager.getInstance().register(new TextDrawListener());
		System.out.println("Hello Fabric world!");

		ModManager modManager = ModManager.getInstance();

		System.out.println("STARTING KITKAT");
		modManager.getMods().forEach(mod -> System.out.println(String.format("  LOADED MOD: %s", mod.getIdentifier())));

		modManager.getSpecialMods().forEach(mod -> System.out.println(String.format("  LOADED SPECIAL MOD: %s", mod.getClass().getSimpleName())));
	}
}
