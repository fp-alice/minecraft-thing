package net.fabricmc.example.client.graphics;

import static org.lwjgl.opengl.GL11.glColor4f;

public class DrawingUtils {

	public static void applyColor(int r, int g, int b, int a){
			glColor4f(r/255.0f, g/255.0f, b/255.0f, a/255.0f);
		}

	public static void applyColor(Color c){
			glColor4f(c.getRF(), c.getGF(), c.getBF(), c.getAF());
		}

}
