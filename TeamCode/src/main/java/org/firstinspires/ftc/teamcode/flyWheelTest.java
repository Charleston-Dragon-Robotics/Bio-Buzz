package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp(name = "flyWheelTest", group = "Teleop")
public class flyWheelTest extends LinearOpMode {

    driveTrain Drive = new driveTrain();
    Launcher Launch = new Launcher();


    @Override
    public void runOpMode() throws InterruptedException {

        Drive.init(this);
        Launch.init(this);


        waitForStart();
        while (opModeIsActive()){
            Launch.test();

        }

    }
}
