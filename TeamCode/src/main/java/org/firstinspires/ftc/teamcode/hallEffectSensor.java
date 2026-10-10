package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class hallEffectSensor {

    private LinearOpMode opmode = null;


    public hallEffectSensor() {
    }

    private TouchSensor Magnetic;

    public void init(LinearOpMode opMode, Boolean Debug) {
        if (Debug) {
            opMode.telemetry.addLine("hallEffectSensor init started");
        }
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        Magnetic = hwMap.touchSensor.get("Magnetic");

        if (Debug) {
            opMode.telemetry.addLine("hallEffectSensor init complete");
        }
    }

    public boolean isTouched(boolean Debug) {
        boolean ret = false;
        if (Magnetic.isPressed()) {
            ret = true;
        }
        if (Debug) {
            opmode.telemetry.addData("Magnetic sensor return", ret);
        }
        return ret;
    }

}
