package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRR;

public class Sub extends Instruction<OperandsRRR> {
    public Sub() {
        super(2, "sub");
    }

    @Override
    public void execute(CPU cpu, OperandsRRR operands) throws InterruptException {

    }
}
