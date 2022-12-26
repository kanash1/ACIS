package instructions.integer.branching;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsC;

public class CALL extends Instruction<OperandsC> {
    public CALL() {
        super(79, "call");
    }

    @Override
    public void execute(CPU cpu, OperandsC operands) throws InterruptException {

    }
}
