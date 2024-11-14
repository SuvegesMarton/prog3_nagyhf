package grid;

public class Grid {
	private String id;
	private final int gridSizeBase;
	private int[] values;
	private boolean[] isHardcoded;

	public Grid(String id, int gridSizeBase) {
		this.id = id;
		this.gridSizeBase = gridSizeBase;
		int numberOfFields = (int)Math.pow(gridSizeBase, 4);
		this.values = new int[numberOfFields];
		this.isHardcoded = new boolean[numberOfFields];
	}

	public Grid(String id) {
		this.id = id;
		this.gridSizeBase = 3;
		int numberOfFields = (int)Math.pow(gridSizeBase, 4);
		this.values = new int[numberOfFields];
		this.isHardcoded = new boolean[numberOfFields];
	}

	private int xyToScalar(int x, int y) {
		return y*this.gridSizeBase*this.gridSizeBase + x;
	}

	public String getID() {
		return this.id;
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

	public void setValue(int x, int y, int newValue) {
		if (newValue<0 || this.gridSizeBase*this.gridSizeBase<newValue) {
			throw new IllegalArgumentException("Field value out of the legal domain.");
		}
		this.values[xyToScalar(x, y)] = newValue;
	}
	
	public void setIsHardCoded(int x, int y, boolean newSetting) {
		this.isHardcoded[xyToScalar(x, y)] = newSetting;
	}

	public void setID(String newID) {
		this.id = newID;
	}

	//the grid may or may not be finished, but no rules are broken yet.
	public boolean isLegal() {
		return ValidityChecks.checkBoxRule(this) &&
			   ValidityChecks.checkColumnRule(this) &&
			   ValidityChecks.checkRowRule(this);
	}
}