package org.firstinspires.ftc.teamcode.subassemblies;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.icu.text.Transliterator;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoGate {
    public Servo servoGate = null;
    private LinearOpMode opmode = null;
//    private RGBLight rgbLight = null;

    public servoGate() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        servoGate = hwMap.servo.get("servoGate");

        servoGate.setPosition(0.8);
        opmode.telemetry.addLine("gate init");
    }

    public void Hold() {
        servoGate.setPosition(0.8);
        opmode.telemetry.addLine("hold function");

//        rgbLight.red();
//        return true;
    }

    public void Control(double Position) {
        servoGate.setPosition(Position);
    }

    public void Active() {
        servoGate.setPosition(0.3);
        opmode.telemetry.addLine("activie function");

//        rgbLight.green();
//        return true;
    }



}

