package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.*;


@Autonomous(name = "April Tags", group = "Autonomous")
public class aprilTagging extends LinearOpMode {
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
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .build();


        waitForStart();
        while (opModeIsActive()) {
//            if  () {
//                telemetry.addData("x", detection.ftcPose.x);
//                telemetry.addData("ID:", detection.id);
//                telemetry.update();
//            }

            if (processor.getDetections().size() > 0) {
                AprilTagDetection detection = processor.getDetections().get(0);
                if (detection.ftcPose.x > 0) {
                    servo.setPower(-1);
                } else if (detection.ftcPose.x < 0) {
                    servo.setPower(1);
                } else {
                    servo.setPower(0);
                }
            }

        }
    }
}

