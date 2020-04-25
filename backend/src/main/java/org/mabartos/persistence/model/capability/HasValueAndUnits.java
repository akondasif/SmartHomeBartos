package org.mabartos.persistence.model.capability;

public interface HasValueAndUnits<T> extends HasValue<T> {

    String getUnits();

    void setUnits(String units);
}
