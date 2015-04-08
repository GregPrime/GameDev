package gp.galligan.game.gfx;

public class Colors {

	public static int getColor(int color1, int color2, int color3, int color4) {
		
		return (getColor(color4) << 24) + (getColor(color3) << 24) + (getColor(color2) << 8) + (getColor(color1));
	}

	private static int getColor(int color) {
		if(color < 0) { return 255; }
		int red = (color/100) % 10;
		int green = (color/10) % 10;
		int blue = color % 10;
		
		
		return (red*36 + green*6 + blue);
	}
}
