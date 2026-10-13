package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import static java.lang.Thread.sleep;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;


public class limeLighters {
    private LinearOpMode opmode = null;

    public Limelight3A limeLighters;

    driveTrain Drive = new driveTrain();


    public limeLighters() {
    }

    public void init(LinearOpMode opMode) {

        opmode = opMode;
        HardwareMap hwMap;
        hwMap = opMode.hardwareMap;

        limeLighters = hwMap.get(Limelight3A.class, "limeLighters");
        opMode.telemetry.setMsTransmissionInterval(11);
        limeLighters.pipelineSwitch(0);
    }

    public void getStatus() {
        limeLighters.start();
        LLStatus status = limeLighters.getStatus();
        opmode.telemetry.addData("Name", "%s", status.getName());
        opmode.telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                status.getTemp(), status.getCpu(), (int) status.getFps());
        opmode.telemetry.addData("Pipeline", "Index: %d, Type: %s",
                status.getPipelineIndex(), status.getPipelineType());
        opmode.telemetry.update();
    }

    public void showObject() {
        limeLighters.start();
        limeLighters.pipelineSwitch(0);
        LLResult result = limeLighters.getLatestResult();
        if (result != null && result.isValid()) {
            List<LLResultTypes.DetectorResult> detections = result.getDetectorResults();

            if (detections != null && !detections.isEmpty()) {
                opmode.telemetry.addData("Object!!", detections.size());


                for (LLResultTypes.DetectorResult detection : detections) {
                    String classname = detection.getClassName();
                    double tx = detection.getTargetXDegrees();
                    double ty = detection.getTargetYDegrees();
                    double area = detection.getTargetArea();

                    opmode.telemetry.addLine(String.format("Match: %s | X: %.2f Y: %.2f Area: %.2f%%",
                            classname, tx, ty, area));

                }
            } else {
                opmode.telemetry.addData("Status", "No targets detected in current frame.");
            }

        } else {
            opmode.telemetry.addLine("Status No valid Limelight data.");

        }
        opmode.telemetry.update();
//

    }
//    public void showObject() {
//        limeLighters.start();
//        limeLighters.pipelineSwitch(1);
//        LLResult result = limeLighters.getLatestResult();
//        if (result.isValid()) {
//            opmode.telemetry.addLine("object detected!!!!!");
//            opmode.telemetry.addData("x value ^_^", result.getTx());
//            opmode.telemetry.addData("y value @_@", result.getTy());
//            opmode.telemetry.update();
//        }

//    public void pipelineCycle() {
//        limeLighters.start();
//        limeLighters.pipelineSwitch(1);
//        LLResult result = limeLighters.getLatestResult();
//        if (result.isValid()) {
//            opmode.telemetry.addLine("blue nectar detected!!!!!");
//            opmode.telemetry.addData("x value ^_^", result.getTx());
//            opmode.telemetry.addData("y value @_@", result.getTy());
//            opmode.telemetry.update();
//        } else {
//            limeLighters.pipelineSwitch(0);
//            if (result.isValid()) {
//                opmode.telemetry.addLine("pollen detected!!!!!");
//                opmode.telemetry.addData("x value ^_^", result.getTx());
//                opmode.telemetry.addData("y value @_@", result.getTy());
//                opmode.telemetry.update();
//            } else {
//                opmode.telemetry.addLine("nothing detected X﹏X");
//                opmode.telemetry.update();

}


//    public void showObjects() {
//        LLResult result = limeLighters.getLatestResult();
//        LLResult[] results = limeLighters.();
//
//        for(Double txFound : result )
//
//
//    }

// opmode.telemetry.addLine("object detected!!!!!");
//            opmode.telemetry.addData("x value ^_^", result.getTx());
//            opmode.telemetry.addData("y value @_@", result.getTy());
//            opmode.telemetry.addData("(fiducial result) O.O", result.getFiducialResults());
//            opmode.telemetry.addData("(detector result) '◡'", result.getDetectorResults());
//            opmode.telemetry.addData("(color result)", result.getColorResults());
//            opmode.telemetry.addData("(timestamp because why not)", result.getTimestamp());
//            opmode.telemetry.update();

// my name is Gino
