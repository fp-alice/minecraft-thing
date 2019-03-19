package net.fabricmc.example;

import com.google.common.eventbus.Subscribe;
import lombok.NoArgsConstructor;
import net.fabricmc.example.events.impl.HudDrawEvent;
import net.minecraft.client.MinecraftClient;

@NoArgsConstructor
public class TextDrawListener {

    @Subscribe
    public void onHudDrawEvent(HudDrawEvent event) {
        System.out.println("EVENT DISPATCHED!");
        MinecraftClient.getInstance().fontRenderer.drawWithShadow("This is a test", 20, 20, 20);
    }
}
