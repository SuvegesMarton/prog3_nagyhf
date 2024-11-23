package grid;
/**
 * This class holds all attributes for a single Grid (one specific sudoku puzzle).
 * name/id - string name of the grid
 * gridSizeBase - the default, well-known sudoku is based on 3x3 boxes, and 3x3 of these boxes. This implementation lets the grid size base number be other numbers aswell, not just 3 with this variable.
 * values - 1d list of the values in the grid.
 * isHardcoded - describes whether a value is the puzzle's or the solution's part. hardcoded values cannot be changed in solve mode.
 */
public class Grid {
	private String id;
	private final int gridSizeBase;
	private final int numberOfFields;
	private int[] values;
	private boolean[] isHardcoded;
	/**
	 * @param id name of the puzzle
	 * @param gridSizeBase the default, well-known sudoku is based on 3x3 boxes, and 3x3 of these boxes. This implementation lets the grid size base number be other numbers aswell, not just 3.
	 */
	public Grid(String id, int gridSizeBase) {
		this.id = id;
		this.gridSizeBase = gridSizeBase;
		this.numberOfFields = (int)Math.pow(gridSizeBase, 4);
		this.values = new int[numberOfFields];
		this.isHardcoded = new boolean[numberOfFields];
	}
	/**
	 * @param id name of the puzzle
	 */
	public Grid(String id) {
		this.id = id;
		this.gridSizeBase = 3;
		this.numberOfFields = (int)Math.pow(gridSizeBase, 4);
		this.values = new int[numberOfFields];
		this.isHardcoded = new boolean[numberOfFields];
	}
	/**
	 * gets xy coordinates on the sudoku grid
	 * @return scalar value, more fit for using with the grids internal representation.
	 */
	public int xyToScalar(int x, int y) {
		return y*this.gridSizeBase*this.gridSizeBase + x;
	}

	public String getID() {
		return this.id;
	}

	public int getGridSizeBase() {
		return this.gridSizeBase;
	}

	public int getValue(int x, int y){
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

	/**
	 * determines if the grid is legal by applying the 3 sudoku rules (row, column, box). unfinished grids can be legal if they don't break the rules.
	 */
	public boolean isLegal() {
		return ValidityChecks.checkBoxRule(this) &&
			   ValidityChecks.checkColumnRule(this) &&
			   ValidityChecks.checkRowRule(this);
	}
}