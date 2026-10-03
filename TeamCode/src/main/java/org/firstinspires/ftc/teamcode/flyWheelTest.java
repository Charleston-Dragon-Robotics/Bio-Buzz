package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "flyWheelTest", group = "Teleop")
public class flyWheelTest extends LinearOpMode {

//    driveTrain Drive = new driveTrain();
    Launcher Launch = new Launcher();


    @Override
    public void runOpMode() throws InterruptedException {

//        Drive.init(this);
        Launch.init(this);


        waitForStart();
        while (opModeIsActive()){
//            Launch.tuningTest(2000,12.8,2,0);
            Launch.velocityTest();
//            Launch.tuningTest(700, 13.65, 0,0);

        }

    }
}
