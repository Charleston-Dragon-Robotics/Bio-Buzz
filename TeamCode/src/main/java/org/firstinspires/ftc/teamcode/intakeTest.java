package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Intake", group = "Teleop")
public class intakeTest extends LinearOpMode {

    boolean Debug = true;

    @Override
    public void runOpMode()throws InterruptedException{

        intake Intake = new intake();
        Intake.init(this, Debug);

        wait
    }
}
