package com.gwwwwt.elements;

import com.gwwwwt.parser.DataGenerator;

/**
 * Every
 */
public interface BaseElement {

    boolean isFixedSize();
    int getFixSize();

    String print();

    void readFromDataGenerator(DataGenerator generator);
}
