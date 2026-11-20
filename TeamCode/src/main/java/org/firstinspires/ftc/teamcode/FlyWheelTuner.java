package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FlyWheelTuner", group = "Teleop")
public class FlyWheelTuner extends LinearOpMode {

    String currentLaunchMode = "Hive";

    //    driveTrain Drive = new driveTrain();
    Launcher Launch = new Launcher();
    boolean Debug = true;

    int VarIDX = 0;

    double changeVal = 0;

    int targetVelocity = 1200;

    double kf = 10.;
    double kp = 0;
    double ki = 0;
    double kd = 0.5;


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

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);


        waitForStart();
        while (opModeIsActive()) {
            if (newGamePad1.start.released && Debug ) {
                Debug = false;
                telemetry.update();
            } else if (newGamePad1.start.released && !Debug) {
                Debug = true;
                telemetry.update();
            }

            if (newGamePad1.a.released){
//                testVal += 5;
            }



        }

    }
}
