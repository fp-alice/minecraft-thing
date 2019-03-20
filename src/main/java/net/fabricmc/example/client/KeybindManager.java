package net.fabricmc.example.client;

import lombok.Getter;
import lombok.NonNull;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class KeybindManager {

	@Getter
	private static KeybindManager instance = new KeybindManager();
	private KeyBindingRegistry keyBindingRegistry = KeyBindingRegistry.INSTANCE;
	private String category = "kitkat";

	private KeybindManager() {
		keyBindingRegistry.addCategory(category);
	}

	public FabricKeyBinding addKeybind(@NonNull String identifier, int key) {
		FabricKeyBinding keyBinding =
				FabricKeyBinding.Builder.create(
						Identifier.create(String.format("kitkat:%s", identifier)),
						InputUtil.Type.KEY_KEYBOARD, // KEYBOARD
						key,
						category)
						.build();

		keyBindingRegistry.register(keyBinding);

		return keyBinding;
	}
}
