package cpu;

import cpu.interrupts.InterruptVectorTable;
import cpu.interrupts.exceptions.InterruptException;
import instructions.Instruction;
import memory.RAM;
import memory.registers.ProgramCounter;
import memory.registers.Register32;
import memory.registers.StatusRegister;
import operands.Operands;

import java.util.ArrayList;
import java.util.List;

public class CPU {
    public final List<Register32> intRegs;
    public final List<Register32> floatRegs;
    public final ProgramCounter programCounter;
    public final StatusRegister statusReg;
    public final RAM memory;
    public final InterruptVectorTable ivt;

    private static final int INT_REGS_COUNT = 32;
    private static final int FLOAT_REGS_COUNT = 32;
    private static final int MEMORY_SIZE = 1024;
    private static final int PROTECTED_MEMORY_SIZE = MEMORY_SIZE / 2;

    // TODO: ports
    public CPU() {
        intRegs = new ArrayList<>(INT_REGS_COUNT);
        for (int count = 0; count < INT_REGS_COUNT; ++count)
            intRegs.add(new Register32());

        floatRegs = new ArrayList<>(FLOAT_REGS_COUNT);
        for (int count = 0; count < FLOAT_REGS_COUNT; ++count)
            floatRegs.add(new Register32());

        programCounter = new ProgramCounter();
        statusReg = new StatusRegister();
        memory = new RAM(MEMORY_SIZE);
        ivt = new InterruptVectorTable();
    }

    // TODO: program end exception
    // эмулятор, пока не умеет остоанвливаться :(
    public void nextInstruction() {
        try {
            int instructionData = memory.getWord(programCounter.postIncrement());
            int opcode = instructionData >>> (Instruction.SIZE - Instruction.OPCODE_SIZE);
            int operandsData = instructionData << Instruction.OPCODE_SIZE;
            Instruction instruction = Instruction.fromOpcode(opcode);
            instruction.execute(this, Operands.createForInstruction(instruction, operandsData));
        } catch (InterruptException e) {
            if (!e.isMaskable() || statusReg.getFlagStatus(Flag.INTERRUPT)) {
                ivt.getInterruptHandler(e.getVector()).handle(this);
            }
        }
    }

    public void clear() {
        for (var reg : intRegs)
            reg.clear();

        for (var reg : floatRegs)
            reg.clear();

        programCounter.clear();
        statusReg.clear();
        memory.clear();
    }
}
