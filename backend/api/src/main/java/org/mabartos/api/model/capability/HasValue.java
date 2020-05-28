/*
 * Copyright (c) 2020.
 * Martin Bartos
 * SmartHome BartOS
 * All rights reserved.
 */

package org.mabartos.api.model.capability;

public interface HasValue<T> {
    T getValue();

    void setValue(T value);
}
