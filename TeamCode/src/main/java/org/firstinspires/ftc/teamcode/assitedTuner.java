package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.subassembly.Launcher;


@TeleOp(name = "assistedTuner", group = "Teleop")
public class assitedTuner extends LinearOpMode {


    private static ElapsedTime stopwatch = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        String currentLaunchMode = "Hive";

        //    driveTrain Drive = new driveTrain();
        Launcher Launch = new Launcher();


        boolean Debug = true;

        int VarIDX = 0;

        double changeVal = 0;

        double maxVelocity = 0;
        int targetVelocity = 1500;

        double idealKf = 0;
        double idealKp = 0;
        double maxError = 0;

        double kf = 0;
        double kp = 0;
        double ki = 0;
        double kd = 0;

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);
        newGamePad1.updateState();

        if (Debug) {
            telemetry.addLine("flyWheelTest init start");
        }

//        Drive.init(this);
        Launch.init(this, Debug);

        if (Debug) {
            telemetry.addLine("flyWheelTest init complete");
        }

        waitForStart();
        while (opModeIsActive()) {
//            telemetry.update();

            if (newGamePad1.start.released && Debug) {
                Debug = false;
                telemetry.update();
            } else if (newGamePad1.start.released && !Debug) {
                Debug = true;
                telemetry.update();
            }

            switch (VarIDX) {
                case 0:

                    maxVelocity = Launch.velocityTest(Debug);
                    telemetry.addData("Max reading: ", maxVelocity);
                    telemetry.update();

                    telemetry.addLine("finished case zero while loop");
                    Launch.stop();
                    kf = 32767 / maxVelocity;
                    VarIDX = 1;
                    break;
                case 1:
                    telemetry.addLine("Started case 1");
                    telemetry.update();
                    idealKf = Launch.kfTest(1800, kf, Debug);
                    telemetry.addData("Ideal KF: ", idealKf);
                    telemetry.update();
                    VarIDX = 2;
                    break;
                case 2:
                    Launch.velocity(0, idealKf, targetVelocity);
                    if (newGamePad1.dpad_left.released && targetVelocity >= 100) {
                        targetVelocity -= 100;
                    } else if (newGamePad1.dpad_right.released && targetVelocity <= (maxVelocity - 100)) {
                        targetVelocity += 100;
                    }
                    if (newGamePad1.left_bumper.released && targetVelocity >= 50) {
                        targetVelocity -= 50;
                    } else if (newGamePad1.right_bumper.released && targetVelocity <= (maxVelocity - 50)) {
                        targetVelocity += 50;
                    }
                    if (newGamePad1.x.released && targetVelocity >= 25) {
                        targetVelocity -= 25;
                    } else if (newGamePad1.b.released && targetVelocity <= (maxVelocity - 25)) {
                        targetVelocity += 25;
                    }
                    if (newGamePad1.a.released && targetVelocity >= 10) {
                        targetVelocity -= 10;
                    } else if (newGamePad1.y.released && targetVelocity <= (maxVelocity - 10)) {
                        targetVelocity += 10;
                    }

                    double error = targetVelocity - Launch.getV();
                    telemetry.addData("TargetVelocity: ", targetVelocity);
                    telemetry.addData("Error: ", error);
                    telemetry.update();
                    newGamePad1.updateState();
                    if (newGamePad1.start.released) {
                        VarIDX = 3;
                    }
                    break;
                case 3:
                    maxError = Launch.errorTuner(idealKf, targetVelocity, Debug);
                    VarIDX = 4;
                case 4:
                    idealKp = Launch.kpTest(idealKf, targetVelocity, maxError, Debug);
                    VarIDX = 5;
                    break;
                case 5:
                    Launch.velocity(idealKp, idealKf, targetVelocity);
                    break;

            }

        }
    }
}