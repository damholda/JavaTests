import java.util.HashSet;

import com.insightinvestment.cellsimulator.Cell;
import com.insightinvestment.cellsimulator.CellSimulator;

import junit.framework.TestCase;


public class JUnitCellSimulatorTest extends TestCase {

	public void testNextGeneration() {
		HashSet<Cell> cells = new HashSet<>();
		cells.add(new Cell(1,2));
        cells.add(new Cell(2,3));
        cells.add(new Cell(3,1));
        cells.add(new Cell(3,2));
        cells.add(new Cell(3,3));
		CellSimulator cellSimulator = new CellSimulator(cells, 10, 10);
		
		HashSet<Cell> expectedNextGeneration = new HashSet<>();
		expectedNextGeneration.add(new Cell(2,1));
		expectedNextGeneration.add(new Cell(2,3));
		expectedNextGeneration.add(new Cell(4,2));
		expectedNextGeneration.add(new Cell(3,2));
		expectedNextGeneration.add(new Cell(3,3));
		
		assertEquals(expectedNextGeneration, cellSimulator.nextGeneration());
		
		expectedNextGeneration.clear();
		expectedNextGeneration.add(new Cell(3,1));
		expectedNextGeneration.add(new Cell(4,3));
		expectedNextGeneration.add(new Cell(2,3));
		expectedNextGeneration.add(new Cell(4,2));
		expectedNextGeneration.add(new Cell(3,2));
		expectedNextGeneration.add(new Cell(3,3));
		
		assertEquals(expectedNextGeneration, cellSimulator.nextGeneration());
	}

}
