package net.fabricmc.example.client.mods;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.fabricmc.example.client.mods.impl.Fullbright;
import net.fabricmc.example.client.mods.impl.Mod;
import net.fabricmc.example.client.mods.impl.Xray;
import net.fabricmc.example.client.mods.special.ActiveModList;
import net.fabricmc.example.client.mods.special.CoordinateOverlay;
import net.fabricmc.example.client.mods.special.KeypressListener;
import net.fabricmc.example.client.mods.special.SpecialMod;
import net.fabricmc.example.events.EventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {

	@Getter
	private static ModManager instance = new ModManager();

	@Getter
	private ArrayList<Mod> mods =
			Lists.newArrayList(
					new Fullbright(),
					new Xray()
			);

	@Getter
	private ArrayList<SpecialMod> specialMods =
			Lists.newArrayList(new ActiveModList(), new CoordinateOverlay(), new KeypressListener());

	private ModManager() {
		EventManager eventManager = EventManager.getInstance();
		specialMods.stream().filter(SpecialMod::isListener).forEach(eventManager::register);
		mods.stream().filter(Mod::isListener).forEach(eventManager::register);
	}

	public List<Mod> getEnabledMods() {
		return mods.stream().filter(Mod::isEnabled).collect(Collectors.toList());
	}
}
