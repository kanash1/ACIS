package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRR;

public class Add extends Instruction<OperandsRRR> {
    public Add() {
        super(89, "add");
    }

    @Override
    public void execute(CPU cpu, OperandsRRR operands) throws InterruptException {
        int result = cpu.intRegs.get(operands.firstSourceRegister).getValue() +
                cpu.intRegs.get(operands.secondSourceRegister).getValue();
        cpu.intRegs.get(operands.destinationRegister).setValue(result);
    }
}
