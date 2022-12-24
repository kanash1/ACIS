package instructions.integer.arithmetic;

import cpu.CPU;
import cpu.Flag;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRC;

public class AUIPC extends Instruction<OperandsRC> {
    public AUIPC() {
        super(9, "auipc");
    }

    // TODO: 24.12.2022
    @Override
    public void execute(CPU cpu, OperandsRC operands) throws InterruptException {
        int fstValue = operands.const20Bit;

        int result = cpu.programCounter.getValue() + (fstValue << 12);

        cpu.intRegs.get(operands.destinationRegister).setValue(result);
    }
}