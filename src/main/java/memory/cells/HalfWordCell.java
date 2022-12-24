package memory.cells;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HalfWordCell implements Cell<Short> {
    public static final int SIZE = 16;
    public static final int BYTES_NUMBER = 2;

    private final List<ByteCell> bytes;

    public HalfWordCell() {
        this((short)0);
    }

    public HalfWordCell(short halfWord) {
        this.bytes = new ArrayList<>();
        var bytes = ByteBuffer.allocate(BYTES_NUMBER).putShort(halfWord).array();
        for (var _byte : bytes) {
            this.bytes.add(new ByteCell(_byte));
        }
    }

    public HalfWordCell(List<ByteCell> bytes) {
        this.bytes = bytes;
    }

    @Override
    public Short getValue() {
        short value = 0;
        for (var _byte : bytes) {
            value = (short)((value << 8) + (_byte.getValue() & 0xFF));
        }
        return value;
    }

    @Override
    public void setValue(Short value) {
        var bytes = ByteBuffer.allocate(2).putShort(value).array();
        for (int i = 0; i < BYTES_NUMBER; ++i) {
            this.bytes.get(i).setValue(bytes[i]);
        }
    }

    @Override
    public String toString() {
        return bytes.get(0).toString() + " " + bytes.get(1).toString();
    }

    @Override
    public void clear() {
        for (var _byte : bytes)
            _byte.clear();
    }

    @Override
    public ByteCell toByteCell() {
        return null;
    }

    @Override
    public HalfWordCell toHalfWord() {
        return null;
    }

    @Override
    public WordCell toWordCell() {
        return null;
    }
}
