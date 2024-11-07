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

	//may be unfinished, but all set values are legal
	public boolean isLegal() {
		int sideLength = this.gridSizeBase*this.gridSizeBase;
		//check columns
		for (int x=0; x<sideLength;x++) {
			for (int y=0; y<sideLength;y++){
				int checkedValue = this.getValue(x, y);
				if (checkedValue==0) {
					continue;
				}
				for (int temp_y=y+1;temp_y<sideLength;temp_y++) {
					if (this.getValue(x, temp_y) == checkedValue) {
						return false;
					}
				}
			}
		}
		//check rows
		for (int y=0; y<sideLength;y++) {
			for (int x=0; x<sideLength;x++){
				int checkedValue = this.getValue(x, y);
				if (checkedValue==0) {
					continue;
				}
				for (int temp_x=x+1;temp_x<sideLength;temp_x++) {
					if (this.getValue(temp_x, y) == checkedValue) {
						return false;
					}
					
				}
			}
		}
		//check boxes
		int[] valuesInBox = new int[sideLength*sideLength];
		for (int y=0; y<this.gridSizeBase;y++) {
			for (int x=0; x<this.gridSizeBase;x++){
				int i = 0;
				//collect the values from the box to make rule checking easier
				for (int yInBox=0;yInBox<this.gridSizeBase;yInBox++) {
					for (int xInBox=0;xInBox<this.gridSizeBase;xInBox++) {
						valuesInBox[i] = this.getValue(gridSizeBase*x+xInBox, gridSizeBase*y+yInBox);
						i += 1;
					}
				}
				//perform the check
				for (int j=0;j<sideLength;j++) {
					int checkedValue = valuesInBox[j];
					if (checkedValue == 0) {
						continue;
					}
					for (int k=j+1;k<sideLength;k++) {
						if (valuesInBox[k]==checkedValue) {
							return false;
						}
					}
				}
			}
		}
		//if all criteria are met, the grid's values are legal.
		return true;
	}
}