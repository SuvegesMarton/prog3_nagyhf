package grid;

public class Grid {
	private final int gridSizeBase;
	private int[] values;
	private boolean[] isHardcoded;

	public Grid(int gridSizeBase) {
		this.gridSizeBase = gridSizeBase;
		int numberOfFields = (int)Math.pow(gridSizeBase, 4);
		this.values = new int[numberOfFields];
		this.isHardcoded = new boolean[numberOfFields];
	}

	private int xyToScalar(int x, int y) {
		return y*this.gridSizeBase*this.gridSizeBase + x;
	}

	public int getGridSizeBase() {
		return this.gridSizeBase;
	}

	public int getValue(int x, int y) {
		return this.values[xyToScalar(x, y)];
	}
	
	public boolean getIsHardCoded(int x, int y) {
		return this.isHardcoded[xyToScalar(x, y)];
	}
    public static void print() {
		System.out.println("Hello Grid!");
	}
}