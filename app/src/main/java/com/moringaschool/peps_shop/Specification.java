package com.moringaschool.peps_shop;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Specification implements Serializable {
    private String cpu;
    private String os;
    private String ram;
    private String gpu;
    private String monitor;
    private String hardDrive;
    private String connectionGate;
    private String keyboard;
    private String battery;
    private float weight;
}
