package instructions.integer.memory;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import memory.cells.ByteCell;
import memory.cells.HalfWordCell;
import memory.registers.Register32;
import operands.OperandsRRC;

// TODO: 25.12.2022
public class LHU extends Instruction<OperandsRRC> {

    public LHU() {
        super(34, "lhu");
    }

    @Override
    public void execute(CPU cpu, OperandsRRC operands) throws InterruptException {
        int upperPart = cpu.intRegs.get(operands.secondRegister).getValue();
        int offset = operands.const12Bit;

        int value = cpu.memory.getHalfWord(upperPart + offset);
        value = ((value << (Register32.SIZE - HalfWordCell.SIZE)) >>> (Register32.SIZE - HalfWordCell.SIZE));

        cpu.intRegs.get(operands.firstRegister).setValue(value);
    }
}
