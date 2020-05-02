package org.mabartos.persistence.model.capability.humidity;

import org.mabartos.general.CapabilityType;
import org.mabartos.persistence.model.CapabilityModel;
import org.mabartos.persistence.model.capability.HasValueAndUnits;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HumidityCapModel extends CapabilityModel implements HasValueAndUnits<Byte> {

    @Column
    private Byte value;

    @Column
    private String units = "%";

    public HumidityCapModel() {
        super();
    }

    public HumidityCapModel(String name, Integer pin) {
        super(name, CapabilityType.HUMIDITY, pin);
    }

    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public void setValue(Byte value) {
        if (value >= 0 && value <= 100) {
            this.value = value;
        }
    }

    @Override
    public String getUnits() {
        return units;
    }

    @Override
    public void setUnits(String units) {
        this.units = units;
    }
}