package com.datacomp.magicfinmart.motor.privatecar.adapter;

/**
 * Created by Rajeev Ranjan on 19/01/2018.
 */

public class PremiumBreakUpAdapterEntity {
    String name;
    String value;

    public PremiumBreakUpAdapterEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
