package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRC;

public class SLTIU extends Instruction<OperandsRRC> {
    public SLTIU() {
        super(7, "sltiu");
    }

    @Override
    public void execute(CPU cpu, OperandsRRC operands) throws InterruptException {
        int fstValue = cpu.intRegs.get(operands.sourceRegister).getValue();
        int secValue = operands.const12Bit;
        int result;

        result = fstValue < secValue ? 1 : 0;

        cpu.intRegs.get(operands.destinationRegister).setValue(result);
    }
}
