package instructions.floating.memory;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import operands.OperandsRR;

public class FCVTSW extends Instruction<OperandsRR> {
    public FCVTSW(int opcode, String mnemonic) {
        super(53, "fcvtsw");
    }

    @Override
    public void execute(CPU cpu, OperandsRR operands) throws InterruptException {
        int cvt = cpu.intRegs.get(operands.firstRegister).getValue();
        cpu.floatRegs.get(operands.secondRegister).setValueAsFloat((float)cvt);
    }
}
