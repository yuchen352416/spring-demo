package pro.yuchen.demo.spring_demo.utils.img;

import net.coobird.thumbnailator.geometry.Position;

import java.awt.*;


class Margin {
	public static int TOP = 150;
	public static int BOTTOM = 150;
	public static int LEFT = 150;
	public static int RIGHT = 150;
}

public enum SelfPositions implements Position {

	TOP_LEFT {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			return new Point(var5 + Margin.LEFT, var7 + Margin.TOP);
		}
	},
	TOP_CENTER {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var9 = var1 / 2 - var3 / 2;
			return new Point(var9 - Margin.LEFT / 2, var7 + Margin.TOP);
		}
	},
	TOP_RIGHT {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var9 = var1 - var3 - var6;
			return new Point(var9 - Margin.RIGHT, var7 + Margin.TOP);
		}
	},
	CENTER_LEFT {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var10 = var2 / 2 - var4 / 2;
			return new Point(var5 + Margin.LEFT, var10 + Margin.TOP / 2);
		}
	},
	CENTER {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var9 = var1 / 2 - var3 / 2;
			int var10 = var2 / 2 - var4 / 2;
			return new Point(var9 + Margin.LEFT / 2, var10 + Margin.TOP / 2);
		}
	},
	CENTER_RIGHT {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var9 = var1 - var3 - var6;
			int var10 = var2 / 2 - var4 / 2;
			return new Point(var9 + Margin.LEFT / 2, var10 - Margin.TOP / 2);
		}
	},
	BOTTOM_LEFT {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var10 = var2 - var4 - var8;
			return new Point(var5 + Margin.LEFT, var10 - Margin.BOTTOM);
		}
	},
	BOTTOM_CENTER {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var9 = var1 / 2 - var3 / 2;
			int var10 = var2 - var4 - var8;
			return new Point(var9 + Margin.LEFT / 2, var10 - Margin.BOTTOM);
		}
	},
	BOTTOM_RIGHT {
		public Point calculate(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
			int var9 = var1 - var3 - var6;
			int var10 = var2 - var4 - var8;
			return new Point(var9 - Margin.RIGHT, var10 - Margin.BOTTOM);
		}
	};

}
