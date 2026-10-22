package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "assistedTuner", group = "Teleop")
public class assitedTuner extends LinearOpMode {

    String currentLaunchMode = "Hive";

    //    driveTrain Drive = new driveTrain();
    Launcher Launch = new Launcher();
   
    private static ElapsedTime stopwatch = new ElapsedTime();

    boolean Debug = true;

    int VarIDX = 0;

    double changeVal = 0;

    double maxVelocity = 0;
    int targetVelocity = 1200;

    double idealKf = 0;

    double kf = 0;
    double kp = 0;
    double ki = 0;
    double kd = 0;

    GamepadStates newGamePad1 = new GamepadStates(gamepad1);
    GamepadStates newGamePad2 = new GamepadStates(gamepad2);

    @Override
    public void runOpMode() throws InterruptedException {

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

            if (newGamePad1.left_bumper.released) {
                VarIDX = VarIDX - 1;
            } else if (newGamePad1.right_bumper.released) {
                VarIDX = VarIDX + 1;
            }

            newGamePad1.updateState();

            switch (VarIDX) {
                case 0:
                    stopwatch.reset();
                    while (stopwatch.time() < 3) {
                        maxVelocity = Launch.velocityTest(Debug);
                        telemetry.addData("Max reading: ", maxVelocity);
                        telemetry.update();
                    }
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
                    Launch.velocity(idealKf, targetVelocity);
                    if(newGamePad1.dpad_left.released && targetVelocity >= 100) {
                        targetVelocity -= 100;
                    } else if (newGamePad1.dpad_right.released && targetVelocity <= (maxVelocity - 100)) {
                        targetVelocity += 100;
                    }

                    double error = targetVelocity - Launch.getV();
                    telemetry.addData("Error: ", error);
                    telemetry.update();
                    break;
            }
//
//            switch (VarIDX % 5) {
//                case 0:
//                    telemetry.addLine("Case: 0, TargetVelocity");
//                    telemetry.addData("Target velocity", targetVelocity);
//                    telemetry.addLine("A = 50 and B = 100 ");
//                    if (newGamePad1.a.released) {
//                        changeVal = 50;
//                        if (Debug){
//                            telemetry.addLine("changeval set to 50");
//                        }
//                    } else if (newGamePad1.b.released) {
//                        changeVal = 100;
//                        if (Debug) {
//                            telemetry.addLine("changeval set to 100");
//                        }
//                    }
//                    if (Debug){
//                        telemetry.addData("changeVal = ",changeVal);
//                    }
//                    telemetry.addLine("Dpad up will add and Dpad down will subtract");
//                    if (newGamePad1.dpad_down.released) {
//                        targetVelocity += changeVal;
//                    } else if (newGamePad1.dpad_up.released) {
//                        targetVelocity -= changeVal;
//                    }
//                    telemetry.update();
//                    break;
//                case 1:
//                    telemetry.addData("Kf", kf);
//                    telemetry.addLine("A = .5 and B = 1 ");
//                    if (newGamePad1.a.released) {
//                        changeVal = .5;
//                    } else if (newGamePad1.b.released) {
//                        changeVal = 1;
//                    }
//                    telemetry.addLine("Dpad up will add and Dpad down will subtract");
//                    if (newGamePad1.dpad_down.released) {
//                        kf += changeVal;
//                    } else if (newGamePad1.dpad_up.released) {
//                        kf -= changeVal;
//                    }
//                    break;
//                case 2:
//                    telemetry.addData("kp", kp);
//                    telemetry.addLine("A = .05 and B = .1 ");
//                    if (newGamePad1.a.released) {
//                        changeVal = .05;
//                    } else if (newGamePad1.b.released) {
//                        changeVal = .1;
//                    }
//                    telemetry.addLine("Dpad up will add and Dpad down will subtract");
//                    if (newGamePad1.dpad_down.released) {
//                        kp += changeVal;
//                    } else if (newGamePad1.dpad_up.released) {
//                        kp -= changeVal;
//                    }
//                    break;
//                case 3:
//                    telemetry.addData("ki", ki);
//                    telemetry.addLine("A = .05 and B = .1 ");
//                    if (newGamePad1.a.released) {
//                        changeVal = .05;
//                    } else if (newGamePad1.b.released) {
//                        changeVal = .1;
//                    }
//                    telemetry.addLine("Dpad up will add and Dpad down will subtract");
//                    if (newGamePad1.dpad_down.released) {
//                        ki += changeVal;
//                    } else if (newGamePad1.dpad_up.released) {
//                        ki -= changeVal;
//                    }
//                    break;
//                case 4:
//                    telemetry.addData("kd", kd);
//                    telemetry.addLine("A = .05 and B = .1 ");
//                    if (newGamePad1.a.released) {
//                        changeVal = .05;
//                    } else if (newGamePad1.b.released) {
//                        changeVal = .1;
//                    }
//                    telemetry.addLine("Dpad up will add and Dpad down will subtract");
//                    if (newGamePad1.dpad_down.released) {
//                        kd += changeVal;
//                    } else if (newGamePad1.dpad_up.released) {
//                        kd -= changeVal;
//                    }
//            }
//            Launch.tuningTest(targetVelocity,kf,kp,ki,kd);
        }
    }
}