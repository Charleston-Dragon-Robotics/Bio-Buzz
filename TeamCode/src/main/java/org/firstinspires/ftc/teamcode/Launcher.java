package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Launcher {

    private DcMotorEx flyWheelM = null;

    private LinearOpMode opmode = null;

    public Launcher(){}

    public void init(LinearOpMode opMode){
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        flyWheelM = (DcMotorEx)hwMap.dcMotor.get("flyWheelM");

        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flyWheelM.setPower(0);

    }

    public void test(){
        flyWheelM.setPower(1);
        double currentV = flyWheelM.getVelocity();
        opmode.telemetry.addData("Velocity (ticks/second: ", currentV);
        opmode.telemetry.update();


    }
}
