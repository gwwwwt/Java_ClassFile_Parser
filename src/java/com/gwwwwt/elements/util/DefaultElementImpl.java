package com.gwwwwt.elements.util;

import com.gwwwwt.elements.BaseElement;
import com.gwwwwt.parser.DataGenerator;

public class DefaultElementImpl implements BaseElement {

    protected byte[][] content;

    @Override
    public boolean isFixedSize() {
        return false;
    }

    @Override
    public int getFixSize() {
        return 0;
    }

    @Override
    public String print() {
        return null;
    }

    protected void processDynamic(DataGenerator generator) {

    }

    @Override
    public void readFromDataGenerator(DataGenerator generator) {
        if(isFixedSize()) {
            content = new byte[1][];
            this.content[0] = generator.getData(getFixSize());
        } else {
            //for constant_pool or field_info, they will begin with a count which indicate length of this attributes.
            int length = ByteUtil.byteArray2Int(generator.getData(2));
            content = new byte[length][];
            processDynamic(generator);
        }
    }
}
