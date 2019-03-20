package net.fabricmc.example.client.graphics;

import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3d;

public class Drawing3D {

	public static void drawFilledBox(double x, double y, double z, double width, double height){
		glBegin(GL_QUADS);

		/* Top */
		glVertex3d(x + width, y + height, z);
		glVertex3d(x + width, y + height, z + width);
		glVertex3d(x, y + height, z);
		glVertex3d(x, y + height, z + width);

		glVertex3d(x + width, y + height, z + width);
		glVertex3d(x + width, y + height, z);
		glVertex3d(x, y + height, z + width);
		glVertex3d(x, y + height, z);

		/* Bottom */
		glVertex3d(x + width, y, z);
		glVertex3d(x + width, y, z + width);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z + width);

		glVertex3d(x + width, y, z + width);
		glVertex3d(x + width, y, z);
		glVertex3d(x, y, z + width);
		glVertex3d(x, y, z);

		/* Front */
		glVertex3d(x + width, y, z);
		glVertex3d(x + width, y + height, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y + height, z);

		glVertex3d(x + width, y + height, z);
		glVertex3d(x + width, y, z);
		glVertex3d(x, y + height, z);
		glVertex3d(x, y, z);

		/* Back */
		glVertex3d(x + width, y, z + width);
		glVertex3d(x + width, y + height, z + width);
		glVertex3d(x, y, z + width);
		glVertex3d(x, y + height, z + width);

		glVertex3d(x + width, y + height, z + width);
		glVertex3d(x + width, y, z + width);
		glVertex3d(x, y + height, z + width);
		glVertex3d(x, y, z + width);

		/* Left */
		glVertex3d(x + width, y, z + width);
		glVertex3d(x + width, y + height, z + width);
		glVertex3d(x + width, y, z);
		glVertex3d(x + width, y + height, z);

		glVertex3d(x + width, y + height, z + width);
		glVertex3d(x + width, y, z + width);
		glVertex3d(x + width, y + height, z);
		glVertex3d(x + width, y, z);

		/* Right */
		glVertex3d(x, y, z + width);
		glVertex3d(x, y + height, z + width);
		glVertex3d(x, y, z);
		glVertex3d(x, y + height, z);

		glVertex3d(x, y + height, z + width);
		glVertex3d(x, y, z + width);
		glVertex3d(x, y + height, z);
		glVertex3d(x, y, z);
		glEnd();
	}

	public static void drawOutlinedBox(double x, double y, double z, double width, double height, Color fillCol, Color outlineCol){
		DrawingUtils.applyColor(fillCol);
		Drawing3D.drawFilledBox(x, y, z, width, height);
		DrawingUtils.applyColor(outlineCol);
		Drawing3D.drawEmptyBox(x, y, z, width, height);
	}

	public static void drawEmptyBox(double x, double y, double z, double width, double height){
		glBegin(GL_LINES);
		/* Front */
		/* Top Line */
		glVertex3d(x, y + height, z);
		glVertex3d(x + width, y + height, z);
		/* Bottom Line*/
		glVertex3d(x, y, z);
		glVertex3d(x + width, y, z);
		/* Left Line */
		glVertex3d(x + width, y, z);
		glVertex3d(x + width, y + height, z);
		/* Right Line */
		glVertex3d(x, y, z);
		glVertex3d(x, y + height, z);

		/* Back */
		/* Top Line */
		glVertex3d(x, y + height, z + width);
		glVertex3d(x + width, y + height, z + width);
		/* Bottom Line */
		glVertex3d(x, y, z + width);
		glVertex3d(x + width, y, z + width);
		/* Left */
		glVertex3d(x + width, y, z + width);
		glVertex3d(x + width, y + height, z + width);
		/* Right */
		glVertex3d(x, y, z + width);
		glVertex3d(x, y + height, z + width);

		/* Left */
		/* Top Line*/
		glVertex3d(x + width, y + height, z);
		glVertex3d(x + width, y + height, z + width);
		/* Bottom Line */
		glVertex3d(x + width, y, z);
		glVertex3d(x + width, y, z + width);

		/* Right  */
		/* Top Line */
		glVertex3d(x, y + height, z);
		glVertex3d(x, y + height, z + width);
		/* Bottom Line */
		glVertex3d(x, y, z);
		glVertex3d(x, y, z + width);
		glEnd();
	}
}
