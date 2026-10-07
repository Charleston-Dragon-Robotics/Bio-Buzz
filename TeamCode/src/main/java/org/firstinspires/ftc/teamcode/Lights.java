package org.firstinspires.ftc.teamcode;

import android.graphics.ColorSpace;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class Lights {

    private Servo rgb = null;

    private LinearOpMode opmode = null;

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opmode.hardwareMap;

        rgb = hwMap.servo.get("RGB");

        rgb.setPosition(0);
    }

    public void colorControl(String color){

        color.equalsIgnoreCase("Red");


        switch (color) {
            case("Red"):
                rgb.setPosition(0.279);
                break;
            case("yellow"):
                rgb.setPosition(0.388);
                break;
            case("green"):
                rgb.setPosition(0.357);
                break;
            case("blue"):
                rgb.setPosition(0.666);
                break;
        }

    }

}
