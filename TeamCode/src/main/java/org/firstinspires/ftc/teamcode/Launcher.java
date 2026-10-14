package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Locale;


public class Launcher {

    private DcMotorEx flyWheelM = null;
    private CRServo turnTableSer = null;

    private LinearOpMode opmode = null;

    public Launcher() {
    }

    public void init(LinearOpMode opMode, boolean Debug) {
        if(Debug){opMode.telemetry.addLine("Launcher init start");}

        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        flyWheelM = (DcMotorEx) hwMap.dcMotor.get("flyWheelM");

        flyWheelM.setDirection(DcMotorSimple.Direction.REVERSE);

        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flyWheelM.setPower(0);

//        turnTableSer = hwMap.CRservo.get("turnTableSer");
        turnTableSer = hwMap.crservo.get("turnTableSer");

        if(Debug){opMode.telemetry.addLine("Launcher init complete");}

    }

    public void velocityTest(boolean Debug) {
        if(Debug){opmode.telemetry.addLine("velocityTest");}
        flyWheelM.setPower(1);
        double currentV = flyWheelM.getVelocity();
        opmode.telemetry.addData("Velocity (ticks/second: ", currentV);

    }
    public void tuningTest(int targetVelocity, double kf, double kp, double ki, double kd) {
        double curent = flyWheelM.getVelocity();
        double error = targetVelocity - curent;
        flyWheelM.setVelocity((error * kf) + (error * kp) + (error * ki) + (error * kd));
        opmode.telemetry.addData("Target speed: ", targetVelocity);
        opmode.telemetry.addData("Current speed: ", curent);
        opmode.telemetry.addData("error: ", error );

    }
    public void hiveLaunch(boolean Debug){
        tuningTest(1700,11.7,0,0, 0.5);
    }
    public void flowerLaunch(boolean Debug){
        tuningTest(950,11.7,0,0, 0.5);
    }
    public void launchControl(String launchMode, boolean Debug){

        String newLaunchMode =launchMode.toLowerCase();

        switch (newLaunchMode){
            case "hive":
                hiveLaunch(Debug);
                opmode.telemetry.addLine("Launch mode hive");

                break;
            case "flower":
                flowerLaunch(Debug);
                opmode.telemetry.addLine("Launch mode hive");

        }
    }
    public void turnTableRotation(double power, boolean Debug){
        turnTableSer.setPower(power);
        if (Debug){opmode.telemetry.addData("turnTableSer power",power);}



    }


}
