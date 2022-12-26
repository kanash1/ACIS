package instructions.integer.branching;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRRC;

// TODO: 25.12.2022
public class JALR extends Instruction<OperandsRRC> {

    public JALR() {
        super(45, "jalr");
    }

    @Override
    public void execute(CPU cpu, OperandsRRC operands) throws InterruptException {
        int tmp = cpu.programCounter.getValue() + 4;
        cpu.programCounter.setValue(cpu.intRegs.get(operands.secondRegister).getValue() + operands.const12Bit);
        cpu.intRegs.get(operands.firstRegister).setValue(tmp);
    }
}
