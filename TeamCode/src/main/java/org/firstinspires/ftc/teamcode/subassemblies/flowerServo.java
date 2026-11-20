package org.firstinspires.ftc.teamcode.subassemblies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class flowerServo {
    private Servo flowerServo1 = null;
    private Servo flowerServo2 = null;
    private LinearOpMode opmode = null;

    public flowerServo() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        flowerServo1 = hwMap.servo.get("flowerServo1");
        flowerServo2 = hwMap.servo.get("flowerServo2");

        flowerServo1.setPosition(0);
        flowerServo2.setPosition(0);


    }

    public  boolean setDown() {
        flowerServo1.setPosition(0.75);
        flowerServo2.setPosition(0.75);
        return true;
    }

    public void control(double pos) {
        flowerServo1.setPosition(pos);
        flowerServo2.setPosition(pos);
    }

    public void setUp() {
        flowerServo1.setPosition(-0.75);
        flowerServo2.setPosition(-0.75);
    }

    public void stop() {
        flowerServo1.setPosition(0);
        flowerServo2.setPosition(0);
    }

}

