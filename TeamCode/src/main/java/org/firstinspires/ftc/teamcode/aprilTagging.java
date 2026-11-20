package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.*;

import java.util.List;
//import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;


@Autonomous(name = "April Tags", group = "Autonomous")
public class aprilTagging extends LinearOpMode {
    Boolean Debug = Boolean.TRUE;
    private CRServo servo = null;

    private AprilTagProcessor processor;


    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "servo");
        processor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .build();


        double previous = 0;
        double kp = 0.075;
        double kd = 0.065;

        waitForStart();
        while (opModeIsActive()) {


            if (processor.getDetections().size() > 0) {
                List<AprilTagDetection> detections = processor.getDetections();
                for (AprilTagDetection detection : detections) {
                    if (detection instanceof AprilTagClusterDetection) {
                        telemetry.update();
                        if (detection.ftcPose != null) {
                            telemetry.addLine("X: " + detection.ftcPose.x);
                            telemetry.addLine("ID: " + ((AprilTagClusterDetection) detection).metadata.name);
                            telemetry.update();

                            double derivative = detection.ftcPose.x - previous;

                            if (detection.ftcPose.x < -0.2) {
//                    servo.setPower(Math.min(Math.max(detection.ftcPose.x - .2, 0), 1));
                                servo.setPower(((detection.ftcPose.x - .2) * kp) + (derivative * kd));
                            } else if (detection.ftcPose.x > 0.2) {
//                    servo.setPower(Math.max(Math.min(detection.ftcPose.x + .2, 0), -1));
                                servo.setPower(((Math.abs(detection.ftcPose.x) - .2) * kp) + (derivative * kd));

                            }
                            previous = detection.ftcPose.x;
                            telemetry.addLine("Null object");
                            telemetry.update();
                        } else {
                            telemetry.addLine("Pose is null");
                            telemetry.update();
                        }
                    }
                }
            } else {
                telemetry.addLine("Nothing found!");
                telemetry.update();
                servo.setPower(0);

            }
        }
    }
}

