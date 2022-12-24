package operands;

import instructions.Instruction;
import memory.registers.Register32;

public class OperandsRRR extends Operands {
    public int destinationRegister;
    public int firstSourceRegister;
    public int secondSourceRegister;

    public OperandsRRR(int operandsData) {
        super(operandsData);
    }

    @Override
    protected void disassemble(int operandsData) {
        destinationRegister = operandsData >>> (Instruction.SIZE - Register32.ADDRESS_SIZE);
        operandsData <<= Register32.ADDRESS_SIZE;
        firstSourceRegister = operandsData >>> (Instruction.SIZE - Register32.ADDRESS_SIZE);
        operandsData <<= Register32.ADDRESS_SIZE;
        secondSourceRegister = operandsData >>> (Instruction.SIZE - Register32.ADDRESS_SIZE);
    }
}
