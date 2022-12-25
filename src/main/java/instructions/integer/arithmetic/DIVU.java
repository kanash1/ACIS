package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.Flag;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRR;

public class DIVU extends Instruction<OperandsRRR> {
    public DIVU() {
        super(15, "divu");
    }

    @Override
    public void execute(CPU cpu, OperandsRRR operands) throws InterruptException {
        int fstValue = cpu.intRegs.get(operands.firstSourceRegister).getValue();
        int secValue = cpu.intRegs.get(operands.secondSourceRegister).getValue();

        if (secValue == 0){
            throw new NullPointerException();
        }

        int result = fstValue / secValue;

        // если result == 0, флаг zero становится равен 1, иначе 0
        cpu.statusReg.setFlagStatus(Flag.ZERO, result == 0);

        // если произошел перенос (переполнение) (result > UnsignedInteger.MAX_VALUE), флаг carry становится равен 1, иначе 0
        // https://stackoverflow.com/questions/31170203/calculating-carry-flag
        // https://stackoverflow.com/questions/69124873/understanding-the-difference-between-overflow-and-carry-flags
        cpu.statusReg.setFlagStatus(Flag.CARRY, (fstValue ^ Integer.MIN_VALUE) < (secValue ^ Integer.MIN_VALUE));

        cpu.intRegs.get(operands.destinationRegister).setValue(result);
    }
}
