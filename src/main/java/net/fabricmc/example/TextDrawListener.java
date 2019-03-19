package net.fabricmc.example;

import com.google.common.eventbus.Subscribe;
import lombok.NoArgsConstructor;
import net.fabricmc.example.events.impl.HudDrawEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.FontRenderer;

@NoArgsConstructor
public class TextDrawListener {

    @Subscribe
    public void onHudDrawEvent(HudDrawEvent event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc != null) {
            FontRenderer fr = mc.fontRenderer;
            if (fr != null) {
                fr.drawWithShadow("This is a test", 20, 20, 20);
            }
        } else {
            System.out.println("MINECRAFT IS NULL?");
        }
    }
}
