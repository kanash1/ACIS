package cpu.interrupts.handlers;

import cpu.CPU;
import cpu.interrupts.exceptions.DivisionByZeroException;

public class DivisionByZeroHandler extends InterruptHandler<DivisionByZeroException> {
    @Override
    public void handle(CPU cpu) {

    }
}
