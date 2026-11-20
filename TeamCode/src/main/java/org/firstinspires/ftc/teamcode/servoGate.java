package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoGate {
    private Servo servoGate = null;
    private LinearOpMode opmode = null;

    public servoGate() {
    }
    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        servoGate = hwMap.servo.get("servoGate");

        servoGate.setPosition(0.5);


    }

    public boolean Close() {
        servoGate.setPosition(1);
        return true;
    }
    public boolean Open() {
        servoGate.setPosition(0.5);
        return true;
    }
}
