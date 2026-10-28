package org.firstinspires.ftc.teamcode;

import android.sax.StartElementListener;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import java.util.List;


public class Launcher {

    private static ElapsedTime stopwatch = new ElapsedTime();

    private DcMotorEx flyWheelM = null;
    private CRServo turnTableSer = null;

    private LinearOpMode opmode = null;

    double maxV = 0;

    double minError = 9999;
    double minKf = 0;
    double minKp = 0;
    double kpidx = 0;

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

        flyWheelM.setDirection(DcMotorSimple.Direction.REVERSE);

        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flyWheelM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flyWheelM.setPower(0);

//        turnTableSer = hwMap.CRservo.get("turnTableSer");
        turnTableSer = hwMap.crservo.get("turnTableSer");


        if (Debug) {
            opMode.telemetry.addLine("Launcher init complete");
        }

    }

    public void setZeroToFloat() {
        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setZeroToBrake() {
        flyWheelM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public double velocityTest(boolean Debug) {
        stopwatch.reset();
        while (stopwatch.time() < 3) {
            if (Debug) {
                opmode.telemetry.addLine("velocityTest");
            }
            flyWheelM.setPower(1);
            double currentV = flyWheelM.getVelocity();

            opmode.telemetry.addData("Velocity (ticks/second: ", currentV);

            if (maxV < currentV) {
                maxV = currentV;
            }
        }
        return maxV;
    }

    public double kfTest(double target, double kf, boolean Debug) {
        kfidx = kf / 2;
        while (kfidx < (2 * kf / 3)) {
            double currentV = flyWheelM.getVelocity();
            double error = target - currentV;
            velocity(0, kfidx, target);
            opmode.telemetry.addData("set velocity: ", target + kfidx * error);
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
            kfidx += 0.1;
        }
        stop();
        return minKf;
    }

    public double kpTest(double kf, double target, boolean Debug) {
        boolean VDip = false;
        while (kpidx < 2) {
            double currentV = flyWheelM.getVelocity();
            double error = target - currentV;
            velocity(kpidx, kf, target);
            opmode.telemetry.addData("set velocity: ", target + kfidx * error);
            opmode.telemetry.addData("KfIdx: ", kf);
            opmode.telemetry.addData("Current Velocity: ", currentV);
            opmode.telemetry.addData("Current error: ", error);
            opmode.telemetry.update();
            opmode.sleep(1000);
            while (!VDip) {

                if (error > 45) {
                    VDip = true;
                    opmode.telemetry.addData("Dip Detected? ", VDip);
                    opmode.telemetry.update();
                    kpidx = 4;

                }
            }
        }
        return minKp;
    }

//    public double KpTest(double targetVelocity, double kf, boolean Debug){
//        set(targetVelocity);
//        opmode.sleep(2000);
//
//        boolean dipDetected    = false;
//        double currentVelocity = 0;
//        double error           = 0;
//        double kpidx           = 0;
//        double idealKp         = 0;
//        double minRiseTime     = 9999;
//
//        double kpMin1 = 9999;
//        double kpMin2 = 9999;
//
//        Dictionary KpValueDict = new Hashtable();
//         List<Boolean> riseTimeListed = new ArrayList<>();
//
//        while ( kpidx <= 2){
//
//            velocity(idealKp, kf, targetVelocity);
//            dipDetected = false;
//            currentVelocity = getV();
//            error = Math.abs(currentVelocity - targetVelocity);
//
//
//
//            if (Debug){opmode.telemetry.addData("Kp:",idealKp);}
//
//            while (!dipDetected){
//                 currentVelocity = getV();
//                 error = Math.abs(currentVelocity - targetVelocity);
//                if(Debug){opmode.telemetry.addData("error: ", error);}
//                opmode.telemetry.update();
//                if (error > 50) {
//                    stopwatch.reset();
//                    dipDetected = true;
//                }
//            }
//
//            if(Debug){opmode.telemetry.addData("error: ", error);}
//            if(Debug){opmode.telemetry.addData("Dip detected? ", dipDetected);}
//            opmode.telemetry.update();
//            if (error <= 25 && error >= -25 ){
//                if(Debug){opmode.telemetry.addLine("In loop *****************");}
//                opmode.telemetry.update();
//                KpValueDict.put((double) kpidx,(double) stopwatch.time());
//                if (minRiseTime > stopwatch.time() ){
//                    minRiseTime = stopwatch.time();
//                    idealKp = kpidx;
//                }
//                kpidx += 0.5;
//
//            }
//
//        }
//        for (int _ = 0; _ < 2; ){
//            riseTimeListed = (List<Boolean>) KpValueDict.elements();
//
//            for (double val = 0; val < riseTimeListed.size();) {
//                if (val < kpMin1) {
//                    kpMin2 = kpMin1;
//                    kpMin1 = val;
//                } else if (val < kpMin2) {
//                    kpMin2 = val;
//                }
//            }
//
//            idealKp = (double)KpValueDict.get((double) kpMin1) / (double)KpValueDict.get((double) kpMin2);
//
//            velocity(idealKp,kf, targetVelocity);
//
//            if (Debug){opmode.telemetry.addData("Kp:",idealKp);}
//
//            while (!dipDetected){
//                currentVelocity = getV();
//                error = Math.abs(currentVelocity - targetVelocity);
//                if(Debug){opmode.telemetry.addData("error: ", error);}
//                if (error > 50) {
//                    stopwatch.reset();
//                    dipDetected = true;
//                }
//            }
//
//            if(Debug){opmode.telemetry.addData("Dip detected? ", dipDetected);}
//            opmode.telemetry.update();
//
//            if(Debug){opmode.telemetry.addData("Dip detected? ", dipDetected);}
//            opmode.telemetry.update();
//            if (error == 0 ) {
//                KpValueDict.put((double) kpidx, (double) minRiseTime);
//                if (minRiseTime > stopwatch.time()) {
//                    minRiseTime = stopwatch.time();
//                    idealKp = kpidx;
//                }
//            }
//
//        }
//
//
//
//        return idealKp;
//    }

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

    public void velocity(double kp, double kf, double target) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kp, 0, 0, kf);
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

    public void set(double Velocity) {
        flyWheelM.setVelocity(Velocity);
    }

    public void stop() {
        flyWheelM.setPower(0);
    }


}
