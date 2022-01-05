package com.publicissapient.recruting.mowitnow.infrastructure;

import java.util.Iterator;

public abstract class InstructionReader {

    protected abstract Iterator<String> getLines();
}
