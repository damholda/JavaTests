package com.insightinvestment.cellsimulator;

public class Cell {
    private final int col;
    private final int row;
 
    int getCol() {
        return col;
    }
 
    int getRow() {
        return row;
    }
    
    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        Cell aliveCell = (Cell) o;
 
        if (col != aliveCell.col) return false;
        if (row != aliveCell.row) return false;
 
        return true;
    }
 
    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }
    
    @Override
    public String toString() {
    	return String.format("Cell [%s,%s]", col, row);
    }
   
}