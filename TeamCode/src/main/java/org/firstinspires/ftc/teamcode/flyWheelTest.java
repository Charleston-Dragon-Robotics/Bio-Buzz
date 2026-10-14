package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "flyWheelTest", group = "Teleop")
public class flyWheelTest extends LinearOpMode {

    String currentLaunchMode = "Hive";

    //    driveTrain Drive = new driveTrain();
    Launcher Launch = new Launcher();
    boolean Debug = false;

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

            if (newGamePad1.start.released && Debug == true){
                Debug = false;
            } else if (newGamePad1.start.released && Debug == false) {
                Debug = true;
            }

//            Launch.tuningTest(1650,11.7,0,0, 0.5);// hive
//            Launch.tuningTest(950,11.7,0,0, 0.5);// flower
        //    Launch.velocityTest(Debug);

            if (gamepad1.a){
                currentLaunchMode = "Hive";
            } else if (gamepad1.b) {
                currentLaunchMode = "flower";
            }
            Launch.launchControl(currentLaunchMode, Debug);
            Launch.turnTableRotation(gamepad1.left_stick_x,Debug);

            telemetry.update();
        }

    }
}
