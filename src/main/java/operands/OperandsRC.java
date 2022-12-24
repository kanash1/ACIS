package operands;

import instructions.Instruction;
import memory.registers.Register32;

public class OperandsRC extends Operands {
    public int destinationRegister;
    public int const20Bit;

    public OperandsRC(int operandsData) {
        super(operandsData);
    }

    @Override
    protected void disassemble(int operandsData) {
        destinationRegister = operandsData >>> (Instruction.SIZE - Register32.ADDRESS_SIZE);
        operandsData <<= Register32.ADDRESS_SIZE;
        const20Bit = operandsData >>> (Instruction.SIZE - 20);
    }
}
