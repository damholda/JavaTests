package com.insightinvestment.cellsimulator;
 
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CellSimulator {
 
    private int simulationStep = 0;
    private final int colNumber;
    private final int rowNumber;
 
    private Set<Cell> activeAliveCells;
 
    public CellSimulator(Set<Cell> activeAliveCells, int colNumber, int rowNumber) {
        this.activeAliveCells = activeAliveCells;
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
    }
 
    public Set<Cell> nextGeneration() {
        Set<Cell> currentlyAliveAliveCells = new HashSet<>();// activeAliveCells;
 
        for (Cell activeAliveCell : activeAliveCells) {
            int neighboursNumber = getAliveNeighboursNumber(activeAliveCell);
            if(neighboursNumber <= 4 && neighboursNumber >= 2)
            	currentlyAliveAliveCells.add(activeAliveCell);   
        }
        
        for (Cell deadNeighbour : getDeadNeighbours()) {
            if (getAliveNeighboursNumber(deadNeighbour) == 3) {
                currentlyAliveAliveCells.add(new Cell(deadNeighbour.getCol(), deadNeighbour.getRow()));
            }
        }
        
        simulationStep++;
        activeAliveCells = currentlyAliveAliveCells;
        return Collections.unmodifiableSet(activeAliveCells);
    }
 
    public int getSimulationStep() {
        return simulationStep;
    }
 
    private Set<Cell> getDeadNeighbours() {
        Set<Cell> deadNeighbours = new HashSet<>();
        for (Cell activeAliveCell : activeAliveCells) {
        	deadNeighbours.addAll(getDeadNeighbours(activeAliveCell));
        }
    	return deadNeighbours;
    }
 
    private Set<Cell> getDeadNeighbours(Cell aliveCell) {
    	Set<Cell> deadNeighbour = new HashSet<>();
    	for(Cell cell : getNeighbours(aliveCell)) {
        	if(!activeAliveCells.contains(cell)){
        		deadNeighbour.add(cell);
        	}
        }
//    	for (int x=-1;x<=1;x++) {
//    		int checkCol = aliveCell.getCol()+x;
//    		if(checkCol<0||checkCol>colNumber) {
//    			continue;
//    		}
//    		for (int y=-1;y<=1;y++) {
//    			int checkRow = aliveCell.getRow()+y;
//    			if(checkRow<0||checkRow>rowNumber) {
//    				continue;
//    			}
//    			if (x==0 && y==0) {
//    				continue;
//    			}
//    			AliveCell cell = new AliveCell(checkCol, checkRow);
//    			if(!activeAliveCells.contains(cell)){
//            		deadNeighbour.add(cell);
//            	}
//    		}
//    	}
    	return deadNeighbour;
	}

	private int getAliveNeighboursNumber(Cell aliveCell) {
        int neighbourNumber = 0;
        for(Cell cell : getNeighbours(aliveCell)) {
        	if(activeAliveCells.contains(cell)){
        		neighbourNumber++;
        	}
        }
//	    for (int x=-1;x<=1;x++) {
//	    	int checkCol = aliveCell.getCol()+x;
//	    	if(checkCol<0||checkCol>colNumber) {
//				continue;
//			}
//	    	for (int y=-1;y<=1;y++) {
//	    		int checkRow = aliveCell.getRow()+y;
//	    		if(checkRow<0||checkRow>rowNumber) {
//	    			continue;
//	    		}
//	    		if (x==0 && y==0) {
//	    			continue;
//	    		}
//	    		neighbourNumber++;
//	    	}
//	    }
    	return neighbourNumber;
    }
	
	private Set<Cell> getNeighbours(Cell aliveCell){
		Set<Cell> neighbours = new HashSet<>();
        for (int x=-1;x<=1;x++) {
        	int checkCol = aliveCell.getCol()+x;
        	if(checkCol<0||checkCol>colNumber) {
    			continue;
    		}
        	for (int y=-1;y<=1;y++) {
        		int checkRow = aliveCell.getRow()+y;
        		if(checkRow<0||checkRow>rowNumber) {
        			continue;
        		}
        		if (x==0 && y==0) {
        			continue;
        		}
        		neighbours.add(new Cell(checkCol, checkRow));
        	}
        }
        return neighbours;
	}
    
    
    
    
    
    public static void main(String[] args) {
        Set<Cell> activeAliveCells = new HashSet<>();
        activeAliveCells.add(new Cell(1,2));
        activeAliveCells.add(new Cell(2,3));
        activeAliveCells.add(new Cell(3,1));
        activeAliveCells.add(new Cell(3,2));
        activeAliveCells.add(new Cell(3,3));
 
        CellSimulator cellSimulator = new CellSimulator(activeAliveCells, 10, 10);
        while(cellSimulator.getSimulationStep() < 20) {
            cellSimulator.nextGeneration();
        }
    }
}