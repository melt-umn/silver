//package agnosis-context.libs.visualization;
package common;

import static java.lang.Math.min;
import static java.lang.Math.max;

/**
 * A FileRange object represents a range of file coordinate between two FileCoordinate objects
 */
public class FileRange {
    private final FileCoordinate coord1, coord2;

    /**
     * Constructs a FileRange object with the bounds of two file coordinates. 
     * The file coordinates need not be supplied in a particular order. 
     * 
     * @param coord1 the first file coordinate bound
     * @param coord2 the second file coordinate bound
     */
    public FileRange(FileCoordinate coord1, FileCoordinate coord2) {
        this.coord1 = coord1;
        this.coord2 = coord2;
    }

    /**
     * Returns if the file range is contained inside of the file range represented
     * by this object.
     * 
     * @param range the range to be checked if it is contained
     * @return if the range is contained in the range represented by this object
     */
    public boolean contains(FileRange range) {
        return this.contains(range.coord1) && this.contains(range.coord2);
    }

    /**
     * Returns if the file coordinate is contained inside of the file range represented
     * by this object.
     * 
     * @param coord the coordinate to be checked if it is contained
     * @return if the coordinate is contained in the range represented by this object
     */
    public boolean contains(FileCoordinate coord) {
        FileCoordinate topLeft = this.getTopLeft();
        FileCoordinate bottomRight = this.getBottomRight();

        if(coord.getRow() < topLeft.getRow() || coord.getRow() > bottomRight.getRow()) return false;
        if(coord.getCol() < topLeft.getCol() || coord.getCol() > bottomRight.getCol()) return false;

        return true;
    }

    /**
     * Returns the file coordinate that represents the top left coordinate of the range.
     * 
     * @return the file coordinate that represents the top left coordinate of the range
     */
    public FileCoordinate getTopLeft() {
        int row = min(coord1.getRow(), coord2.getRow());
        int col = min(coord1.getCol(), coord2.getCol());

        if(coord1.getRow() == row && coord1.getCol() == col) return coord1;
        if(coord2.getRow() == row && coord2.getCol() == col) return coord2;

        return new FileCoordinate(row, col);
    }

    /**
     * Returns the file coordinate that represents the bottom right coordinate of the range.
     * 
     * @return the file coordinate that represents the bottom right coordinate of the range
     */
    public FileCoordinate getBottomRight() {
        int row = max(coord1.getRow(), coord2.getRow());
        int col = max(coord1.getCol(), coord2.getCol());

        if(coord1.getRow() == row && coord1.getCol() == col) return coord1;
        if(coord2.getRow() == row && coord2.getCol() == col) return coord2;

        return new FileCoordinate(row, col);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.getTopLeft(), this.getBottomRight());
    }
}
