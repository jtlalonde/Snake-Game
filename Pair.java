// Pair.java 
//
// This is a helper class for my snake queue

public class Pair {
	private int row;
	private int col;

	public Pair (int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}	

	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Pair)) {
			return false;
		}

		Pair other = (Pair) obj;
		return this.row == other.row && this.col == other.col;
	}

	@Override
    public int hashCode() {
        return 31 * row + col;
    }
}
