package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.events.EventManager;

public class ExampleMod implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
//		EventManager.getInstance().register(new TextDrawListener());
		System.out.println("Hello Fabric world!");
	}
}
