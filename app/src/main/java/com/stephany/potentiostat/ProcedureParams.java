package com.stephany.potentiostat;

import java.io.Serializable;

public class ProcedureParams implements Serializable {

    String name;
    int wait;
    int duration;
    int scanRate;

    public ProcedureParams(String name, int wait, int duration, int scanRate) {
        this.name = name;
        this.wait = wait;
        this.duration = duration;
        this.scanRate = scanRate;
    }

    public String getName() {
        return name;
    }

    public int getWait() {
        return wait;
    }

    public int getDuration() {
        return duration;
    }

    public int getScanRate() {
        return scanRate;
    }
}
