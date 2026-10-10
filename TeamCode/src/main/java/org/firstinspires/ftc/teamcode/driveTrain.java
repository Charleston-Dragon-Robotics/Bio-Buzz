package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrain {

    //    Create motor objects here
    private DcMotor FrontRM = null;
    private DcMotor FrontLM = null;
    private DcMotor BackRM = null;
    private DcMotor BackLM = null;

    private LinearOpMode opmode = null;


    public driveTrain() {
    }

    public void init(LinearOpMode opMode, Boolean Debug) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        // name motor objects here
        FrontRM = hwMap.dcMotor.get("FrontRM");
        FrontLM = hwMap.dcMotor.get("FrontLM");
        BackRM = hwMap.dcMotor.get("BackRM");
        BackLM = hwMap.dcMotor.get("BackLM");


        // directions!!!
        FrontLM.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontRM.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLM.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRM.setDirection(DcMotorSimple.Direction.FORWARD);

        FrontLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set motor powers to 0
        FrontRM.setPower(0);
        FrontLM.setPower(0);
        BackRM.setPower(0);
        BackLM.setPower(0);

        if (Debug == Boolean.TRUE){opmode.telemetry.addLine("driveTrain initalization");}
    }

    public void multi(double y, double x, double yaw, Boolean Debug) {
        FrontLM.setPower(y + x + yaw);
        if (Debug == Boolean.TRUE) {
            opmode.telemetry.addData("FrontLM power", y + x + yaw);
        }
        BackLM.setPower(y - x + yaw);
        if (Debug == Boolean.TRUE) {
            opmode.telemetry.addData("BackLM power", y - x + yaw);
        }
        FrontRM.setPower(y - x - yaw);
        if (Debug == Boolean.TRUE) {
            opmode.telemetry.addData("FrontRM power", y - x - yaw);
        }
        BackRM.setPower(y + x - yaw);
        if (Debug == Boolean.TRUE) {
            opmode.telemetry.addData("BackRM power", y + x - yaw);
        }

    }
}