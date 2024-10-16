//package agnosis-context.libs.visualization;
package common;
import static java.lang.Math.max;

/**
 * A FileCoordinate object represents a coordinate in a file as a point (character) in a file
 * specified by a row and a column.
 */
public class FileCoordinate {
    private final int row, col;

    /**
     * Constucts a FileCoordinate object with a row and column
     * 
     * @param row the row of the coordinate
     * @param col the column of the coordinate
     */
    public FileCoordinate(int row, int col) {
        if(row < 0 || col < 0) {
            this.row = max(row, 0);
            this.col = max(col, 0);
        } else {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Returns the row of the coordinate
     * 
     * @return the row of the coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the coordinate
     * 
     * @return the column of the coordinate
     */
    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj.getClass() != FileCoordinate.class) {
            return false;
        }

        FileCoordinate objFileCoordinate = (FileCoordinate) obj;

        return this.row == objFileCoordinate.row && this.col == objFileCoordinate.col;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.row, this.col);
    }
}
