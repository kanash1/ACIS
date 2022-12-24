package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.Flag;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRC;

public class Addi extends Instruction<OperandsRRC> {
    public Addi() {
        super(3, "addi");
    }

    @Override
    public void execute(CPU cpu, OperandsRRC operands) throws InterruptException {
        int fstValue = cpu.intRegs.get(operands.sourceRegister).getValue();
        int secValue = cpu.intRegs.get(operands.const12Bit).getValue();

        int result = fstValue + secValue;

        // если result == 0, флаг zero становится равен 1, иначе 0
        cpu.statusReg.setFlagStatus(Flag.ZERO, result == 0);

        // если произошло переполнение result > Integer.MAX_VALUE или result < Integer.MIN_VALUE,
        // флаг overflow становится равен 1, иначе 0
        // https://stackoverflow.com/questions/3001836/how-does-java-handle-integer-underflows-and-overflows-and-how-would-you-check-fo
        cpu.statusReg.setFlagStatus(Flag.OVERFLOW, ((fstValue & secValue & ~result) | (~fstValue & ~secValue & result)) < 0);

        // если произошел перенос (переполнение) (result > UnsignedInteger.MAX_VALUE), флаг carry становится равен 1, иначе 0
        // https://stackoverflow.com/questions/31170203/calculating-carry-flag
        // https://stackoverflow.com/questions/69124873/understanding-the-difference-between-overflow-and-carry-flags
        cpu.statusReg.setFlagStatus(Flag.CARRY, (fstValue ^ Integer.MIN_VALUE) < (secValue ^ Integer.MIN_VALUE));

        // если result < 0 (именно в int формате), флаг sign становится равен 1, иначе 0
        cpu.statusReg.setFlagStatus(Flag.SIGN, result < 0);

        cpu.intRegs.get(operands.destinationRegister).setValue(result);
    }
}
