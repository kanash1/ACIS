package memory.registers;

import cpu.Flag;

public class StatusRegister extends Register8 {
    public boolean getFlagStatus(Flag flag) {
        return (getValue() & flag.getValue()) != 0;
    }

    public void setFlagStatus(Flag flag, boolean status) {
        setValue((byte)((status) ? (getValue() | flag.getValue()) : (getValue() & ~flag.getValue())));
    }

    public void invertFlagStatus(Flag flag) {
        setFlagStatus(flag, !getFlagStatus(flag));
    }

    // TODO: некоректно отображается
    @Override
    public String toString() {
        return super.toString().replace("0", "0    ").trim();
    }
}
