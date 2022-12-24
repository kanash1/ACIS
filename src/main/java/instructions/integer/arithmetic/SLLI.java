package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.Flag;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRC;

public class SLLI extends Instruction<OperandsRRC> {
    public SLLI() {
        super(27, "slli");
    }

    @Override
    public void execute(CPU cpu, OperandsRRC operands) throws InterruptException {
        int fstValue = cpu.intRegs.get(operands.sourceRegister).getValue();
        int secValue = operands.const12Bit;

        int result = fstValue >> secValue;

        // если result == 0, флаг zero становится равен 1, иначе 0
        cpu.statusReg.setFlagStatus(Flag.ZERO, result == 0);

        // если result < 0 (именно в int формате), флаг sign становится равен 1, иначе 0
        cpu.statusReg.setFlagStatus(Flag.SIGN, result < 0);

        cpu.intRegs.get(operands.destinationRegister).setValue(result);
    }
}
