package cpu.interrupts.handlers;

import cpu.CPU;
import cpu.interrupts.exceptions.UnknownInstructionException;

public class UnknownInstructionHandler extends InterruptHandler<UnknownInstructionException> {
    @Override
    public void handle(CPU cpu) {

    }
}
