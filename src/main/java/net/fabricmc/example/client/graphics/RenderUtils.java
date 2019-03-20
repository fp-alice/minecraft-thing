package net.fabricmc.example.client.graphics;

import lombok.val;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {
	public static void start3D() {
		glPushMatrix();
		glEnable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST);
		glDepthMask(false);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void end3D() {
		glDepthMask(true);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glPopMatrix();
	}

	public static void startSmooth() {
		glEnable(GL_LINE_SMOOTH);
		glEnable(GL_POINT_SMOOTH);
		glEnable(GL_BLEND);
	}

	public static void endSmooth() {
		glDisable(GL_LINE_SMOOTH);
		glDisable(GL_POINT_SMOOTH);
		glDisable(GL_BLEND);
	}

	public static void color(int color) {
		val red = ((float) (color >> 16 & 255)) / 255.0F;
		val green = ((float) (color >> 8 & 255)) / 255.0F;
		val blue = ((float) (color & 255)) / 255.0F;
		val alpha =  ((float) (color >> 24 & 255)) / 255.0F;
		glColor4f(red, green, blue, alpha);
	}

	public static void color(int color, float alpha) {
		val red = ((float) (color >> 16 & 255)) / 255.0F;
		val green = ((float) (color >> 8 & 255)) / 255.0F;
		val blue = ((float) (color & 255)) / 255.0F;
		glColor4f(red, green, blue, alpha);
	}

	public static void drawRect(double x , double y , double x1 , double y1) {
		glBegin(GL_QUADS);
		glVertex2d(x, y1);
		glVertex2d(x1, y1);
		glVertex2d(x1, y);
		glVertex2d(x, y);
		glEnd();
	}

	public static void drawBox(double x, double y, double z, double size, double partialTicks) {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		ClientPlayerEntity player = minecraftClient.player;
		val pos = new RenderPosition(minecraftClient.player, partialTicks);
		val _x = x - pos.x;
		val _y = (y - player.getStandingEyeHeight()) - pos.y;
		val _z = z - pos.z;
		drawBox(_x, _y, _z, _x + size, _y + size, _z + size);
	}

	public static void drawBox(double x , double y , double z , double x2 , double y2 , double z2 ) {
		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);

		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);

		glVertex3d(x, y2, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y2, z);

		glVertex3d(x, y, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y, z);

		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);

		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();
	}

	public static void drawSquare(double _x, double _y, double _z) {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		ClientPlayerEntity player = minecraftClient.player;
		val pos = new RenderPosition(player, 0);
		val x = _x - pos.x;
		val y = (_y - player.getStandingEyeHeight()) - pos.y;
		val z = _z - pos.z;

		glLineWidth(1.5F);
		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x + 1, y, z);
		glVertex3d(x + 1, y, z + 1);
		glVertex3d(x, y, z + 1);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z + 1);
		glVertex3d(x + 1, y, z + 1);
		glVertex3d(x + 1, y, z);
		glEnd();
	}

	public static void drawOutlinedBox(double x , double y, double z, double size, double partialTicks) {
		drawOutlinedBox(x, y, z, x + size, y + size, z + size, partialTicks);
	}

	public static void drawOutlinedBox(double _x , double _y , double _z , double _x2 , double _y2 , double _z2 , double partialTicks)  {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		ClientPlayerEntity player = minecraftClient.player;
		val pos = new RenderPosition(player, partialTicks);
		val x = _x - pos.x;
		val y = (_y - player.getStandingEyeHeight()) - pos.y;
		val z = _z - pos.z;

		val x2 = _x2 - pos.x;
		val y2 = (_y2 - player.getStandingEyeHeight()) - pos.y;
		val z2 = _z2 - pos.z;

		glLineWidth(1.5F);

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);

		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);

		glVertex3d(x, y2, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y2, z);

		glVertex3d(x, y, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y, z);

		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);

		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();
	}
}
