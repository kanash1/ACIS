package cpu.interrupts.handlers;

import cpu.CPU;
import cpu.interrupts.exceptions.InterruptException;

public abstract class InterruptHandler<T extends InterruptException> {
    public abstract void handle(CPU cpu);
}
