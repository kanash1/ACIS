package operands;

import instructions.Instruction;
import memory.registers.Register32;

public class OperandsRRC extends Operands {
    public int destinationRegister;
    public int sourceRegister;
    public int const12Bit;

    public OperandsRRC(int operandsData) {
        super(operandsData);
    }

    @Override
    protected void disassemble(int operandsData) {
        destinationRegister = operandsData >>> (Instruction.SIZE - Register32.ADDRESS_SIZE);
        operandsData <<= Register32.ADDRESS_SIZE;
        sourceRegister = operandsData >>> (Instruction.SIZE - Register32.ADDRESS_SIZE);
        operandsData <<= Register32.ADDRESS_SIZE;
        const12Bit = operandsData >>> (Instruction.SIZE - 12);
    }
}
