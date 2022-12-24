package cpu.interrupts.exceptions;

public class DivisionByZeroException extends InterruptException {
    protected DivisionByZeroException(String errorMessage) {
        super(0, false, errorMessage);
    }
}
