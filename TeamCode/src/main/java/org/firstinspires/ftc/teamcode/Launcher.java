package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;


public class Launcher {

    private DcMotorEx flyWheelM = null;
    private CRServo turnTableSer = null;

    private LinearOpMode opmode = null;

    double maxV = 0;

    double minError = 1000;
    double minKf = 0;

    double kfidx = 0;

    public Launcher() {
    }

    public void init(LinearOpMode opMode, boolean Debug) {
        if (Debug) {
            opMode.telemetry.addLine("Launcher init start");
        }

        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        flyWheelM = (DcMotorEx) hwMap.dcMotor.get("flyWheelM");

        flyWheelM.setDirection(DcMotorSimple.Direction.FORWARD);

        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flyWheelM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flyWheelM.setPower(0);

//        turnTableSer = hwMap.CRservo.get("turnTableSer");
        turnTableSer = hwMap.crservo.get("turnTableSer");


        if (Debug) {
            opMode.telemetry.addLine("Launcher init complete");
        }

    }

    public double velocityTest(boolean Debug) {
        if (Debug) {
            opmode.telemetry.addLine("velocityTest");
        }
        flyWheelM.setPower(1);
        double currentV = flyWheelM.getVelocity();

        opmode.telemetry.addData("Velocity (ticks/second: ", currentV);

        if (maxV < currentV) {
            maxV = currentV;
        }

        return maxV;
    }

    public double kfTest(double target, double kf, boolean Debug) {
        kfidx = kf / 2;
        while (kfidx < (2 * kf)) {
            double currentV = flyWheelM.getVelocity();
            double error = target - currentV;
            velocity(kfidx, target);
            opmode.telemetry.addData("set velocity: ",target + kfidx * error);
            opmode.telemetry.addData("KfIdx: ", kfidx);
            opmode.telemetry.addData("Current Velocity: ", currentV);
            opmode.telemetry.addData("Current error: ", error);
//            opmode.telemetry.update();
            opmode.sleep(800);
            if (error < minError && 0 <= error) {
                minError = error;
                minKf = kfidx;
            }
            opmode.telemetry.addData("Min error: ", minError);
            opmode.telemetry.addData("Min KF: ", minKf);
            opmode.telemetry.update();
            kfidx += 0.5;
        }
        stop();
        return minKf;
    }

    public double getV() {
        return flyWheelM.getVelocity();
    }

    public void tuningTest(int targetVelocity, double kf, double kp, double ki, double kd) {
        double curent = flyWheelM.getVelocity();
        double error = targetVelocity - curent;
        flyWheelM.setVelocity((targetVelocity * kf / 60) + (error * kp) + (error * ki) + (error * kd));
        opmode.telemetry.addData("Target speed: ", targetVelocity);
        opmode.telemetry.addData("Current speed: ", curent);
        opmode.telemetry.addData("error: ", error);

    }

    public void velocity(double kf, double target) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0, 0, 0 ,kf);
        flyWheelM.setVelocity(target);
    }

    public void hiveLaunch(boolean Debug) {
        //tuningTest(1700,11.7,0,0, 0.5);
        int targetVelocity = 1700;
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- Target launch velocity", targetVelocity);
        }
        double kf = 11.7;
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- kf tuner", kf);
        }
        double kp = 0;
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- kp tuner", kp);
        }
        double ki = 0;
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- ki tuner", ki);
        }
        double kd = 0.5;
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- kd tuner", kd);
        }
        double curent = flyWheelM.getVelocity();
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- Current launcher velocity", curent);
        }
        double error = targetVelocity - curent;
        if (Debug) {
            opmode.telemetry.addData("    --hiveLaunch-- error from target velocity", error);
        }
        flyWheelM.setVelocity((error * kf) + (error * kp) + (error * ki) + (error * kd));
    }

    public void flowerLaunch(boolean Debug) {
        tuningTest(950, 11.7, 0, 0, 0.5);
        int targetVelocity = 950;
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- Target launch velocity", targetVelocity);
        }
        double kf = 11.7;
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- kf tuner", kf);
        }
        double kp = 0;
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- kp tuner", kp);
        }
        double ki = 0;
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- ki tuner", ki);
        }
        double kd = 0.5;
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- kd tuner", kd);
        }
        double curent = flyWheelM.getVelocity();
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- Current launcher velocity", curent);
        }
        double error = targetVelocity - curent;
        if (Debug) {
            opmode.telemetry.addData("    --flowerLaunch-- error from target velocity", error);
        }
        flyWheelM.setVelocity((error * kf) + (error * kp) + (error * ki) + (error * kd));
    }

    public void launchControl(String launchMode, boolean Debug) {

        String newLaunchMode = launchMode.toLowerCase();

        switch (newLaunchMode) {
            case "hive":
                hiveLaunch(Debug);
                opmode.telemetry.addLine("Launch mode hive");

                break;
            case "flower":
                flowerLaunch(Debug);
                opmode.telemetry.addLine("Launch mode hive");

        }
    }

    public void turnTableRotation(double power, boolean Debug) {
        turnTableSer.setPower(power);
        if (Debug) {
            opmode.telemetry.addData("turnTableSer power", power);
        }
    }

    public void set(int Velocity) {
        flyWheelM.setVelocity(Velocity);
    }

    public void stop() {
        flyWheelM.setPower(0);
    }


}
