package org.firstinspires.ftc.teamcode;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto", group = "Autonomous")
public class Auto extends LinearOpMode {
    boolean red = false;
    boolean blue = false;
    boolean left = false;
    boolean right = false;
    boolean colorSelected = false;
    boolean locationSelected = false;
    boolean confirmed = false;
    boolean telemetryFix = false;


    @Override
    public void runOpMode() throws InterruptedException {
        // declare subassembly classes
        // subassembly class

        // name subassembly classes
        // name

        autoBlueRight ABFar = new autoBlueRight();

        telemetry.addLine("press X for blue, press B for red");
        telemetry.update();

        while (!colorSelected) {
            if (gamepad1.bWasReleased()) {
                red = true;
                colorSelected = true;
            } else {
                if (gamepad1.xWasReleased()) {
                    blue = true;
                    colorSelected = true;
                }
            }
        }
        telemetry.addLine("press Up for Far, press Down for Close");
        telemetry.update();

        while (!locationSelected) {
            if (gamepad1.dpadDownWasReleased()) {
                left = true;
                locationSelected = true;
            } else {
                if (gamepad1.dpadUpWasReleased()) {
                    right = true;
                    locationSelected = true;
                }
            }


            waitForStart();

            while (opModeIsActive()) {
                while (!confirmed) {
                    if (blue && left) {
                        telemetry.addLine("blue left selected :]");
                        confirmed = true;
                    } else if (blue && right) {
                        telemetry.addLine("blue right selected :]");
                        confirmed = true;
                    } else if (red && left) {
                        telemetry.addLine("red left without saying goodbye :(");
                        confirmed = true;
                    } else {
                        telemetry.addLine("red isnt right because blue is a better color but ok i guess :/");
                        confirmed = true;
                    }
                }
                if (!telemetryFix) {
                    telemetry.addLine("auto started wow");
                    telemetry.update();
                    telemetryFix = true;
                }
            }
            if (blue && right) {
//                ABFar.autoRoutine();
                Follower follower = ABFar.follower;


                Scheduler.reset();
                follower = Constants.create(hardwareMap);
                follower.setPose(ABFar.startBlueRight);
//                schedule(follow(follower, ABFar.launchToFlowerABFar()));
//                schedule(follow(follower, ABFar.flowerToLaunch()));
//                schedule(follow(follower, ABFar.launchToFlower2()));
//                schedule(follow(follower, ABFar.flowerToLaunch2()));
//                schedule(follow(follower, ABFar.launchToPark()));

                schedule(ABFar.autoBlueRight());

                while (opModeIsActive()) {
                    follower.update();
                    Scheduler.execute();
                }
            }
        }
    }
}
