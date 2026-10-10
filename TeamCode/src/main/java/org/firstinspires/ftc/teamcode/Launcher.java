package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Launcher {

    private DcMotorEx flyWheelM = null;

    private LinearOpMode opmode = null;

    public Launcher() {
    }

    public void init(LinearOpMode opMode, boolean Debug) {
        if(Debug){opMode.telemetry.addLine("Launcher init start");}

        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        flyWheelM = (DcMotorEx) hwMap.dcMotor.get("flyWheelM");

        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flyWheelM.setPower(0);

        if(Debug){opMode.telemetry.addLine("Launcher init complete");}
        opMode.telemetry.update();
    }

    public void velocityTest(boolean Debug) {
        if(Debug){opmode.telemetry.addLine("velocityTest");}
        flyWheelM.setPower(1);
        double currentV = flyWheelM.getVelocity();
        opmode.telemetry.addData("Velocity (ticks/second: ", currentV);
        opmode.telemetry.update();
    }
    public void speedControlTest() {
        flyWheelM.setPower(1);
        double currentV = flyWheelM.getVelocity();
        opmode.telemetry.addData("Velocity (ticks/second: ", currentV);
        opmode.telemetry.update();
    }
    public void tuningTest(int targetVelocity, double kf, double kp, double ki) {
        double curent = flyWheelM.getVelocity();
        double error = targetVelocity - curent;
        flyWheelM.setVelocity((error * kf) + (error * kp) + (error * ki));
        opmode.telemetry.addData("Target speed: ", targetVelocity);
        opmode.telemetry.addData("Current speed: ", curent);
        opmode.telemetry.addData("error: ", error );
        opmode.telemetry.update();
    }

}
