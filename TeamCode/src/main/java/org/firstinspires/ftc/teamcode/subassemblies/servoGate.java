package org.firstinspires.ftc.teamcode.subassemblies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoGate {
    private Servo servoGate = null;
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


    }

    public boolean Hold() {
        servoGate.setPosition(0.8);
//        rgbLight.red();
        return true;
    }

    public boolean Active() {
        servoGate.setPosition(0.3);
//        rgbLight.green();
        return true;
    }


}

