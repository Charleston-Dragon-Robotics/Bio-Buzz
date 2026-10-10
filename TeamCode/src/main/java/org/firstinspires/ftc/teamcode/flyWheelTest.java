package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "flyWheelTest", group = "Teleop")
public class flyWheelTest extends LinearOpMode {

    //    driveTrain Drive = new driveTrain();
    Launcher Launch = new Launcher();
    boolean Debug = false;

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
//            Launch.tuningTest(2000,12.8,2,0);
            Launch.velocityTest(Debug);
//            Launch.tuningTest(700, 13.65, 0,0);

        }

    }
}
