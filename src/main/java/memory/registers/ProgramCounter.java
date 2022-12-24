package memory.registers;

import memory.cells.ByteCell;
import memory.cells.WordCell;

public class ProgramCounter extends Register32 {
    public int postIncrement() {
        int result = getValue();
        setValue(result + WordCell.SIZE / ByteCell.SIZE);
        return result;
    }
}
