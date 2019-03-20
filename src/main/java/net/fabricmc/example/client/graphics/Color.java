package net.fabricmc.example.client.graphics;

public class Color {
	private final int r, g, b, a;

	public Color(int r, int g, int b){
		this(r, g, b, 255);
	}

	public Color(int r, int g, int b, int a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public Color modifyAlpha(int alpha){
		return new Color(r, g, b, alpha);
	}

	public float getRF(){
		return r/255.0f;
	}

	public float getBF(){
		return b/255.0f;
	}

	public float getGF(){
		return g/255.0f;
	}

	public float getAF(){
		return a/255.0f;
	}

}
