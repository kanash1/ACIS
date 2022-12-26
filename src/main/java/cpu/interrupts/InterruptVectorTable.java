package cpu.interrupts;

import cpu.interrupts.exceptions.InterruptException;
import cpu.interrupts.handlers.InterruptHandler;
import operands.Operands;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class InterruptVectorTable {
    private Map<Integer, InterruptHandler> table;

    public InterruptVectorTable() {
        Map<Integer, InterruptHandler> table = new HashMap<>();
        try {
            var reflections = new Reflections("cpu.interrupts");
            var subclasses = reflections.getSubTypesOf(InterruptHandler.class);
            for (var interruptHandlerClass : subclasses) {
                var interruptHandler = interruptHandlerClass.getConstructor().newInstance();
                Class<? extends InterruptException> interruptException =
                        (Class<? extends InterruptException>) ((ParameterizedType)interruptHandler
                                .getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                table.put(interruptException.getConstructor(String.class).newInstance("").getVector(),
                        interruptHandlerClass.getConstructor().newInstance());
            }
            this.table = Collections.unmodifiableMap(table);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public InterruptHandler getInterruptHandler(int vector) {
        return table.get(vector);
    }
}
