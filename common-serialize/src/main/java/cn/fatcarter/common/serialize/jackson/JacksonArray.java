package cn.fatcarter.common.serialize.jackson;

import java.util.ArrayList;
import java.util.Collection;

public class JacksonArray extends ArrayList<JacksonObject> {

    public JacksonArray() {
    }

    public JacksonArray(int initialCapacity) {
        super(initialCapacity);
    }

    public JacksonArray(Collection<? extends JacksonObject> c) {
        super(c);
    }
}
