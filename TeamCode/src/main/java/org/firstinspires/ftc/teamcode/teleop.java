package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GamepadStates;
import org.firstinspires.ftc.teamcode.subassembly.Intake;
import org.firstinspires.ftc.teamcode.subassembly.driveTrain;
import org.firstinspires.ftc.teamcode.subassembly.Launcher;
import org.firstinspires.ftc.teamcode.subassembly.servoGate;

import java.sql.Driver;


@TeleOp(name = "Teleop", group = "Teleop")
public class teleop extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        // declare subassembly classes
        driveTrain Drive = new driveTrain();
        Intake Intake = new Intake();
        Launcher Launch = new Launcher();

        // names subassembly classes
        Drive.init(this);
        Intake.init(this);
        Launch.init(this, false);

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

        //code for the auto phase
        waitForStart();
//
//        Fun.forwardForDist(12, .5);

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();

            // movement based on driveTrain.multi
            if (gamepad1.right_stick_y > .1 || gamepad1.right_stick_x > .1 || gamepad1.left_stick_x > .1) {
                Drive.multi(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
            } else {
                Drive.stop();
            }

            if (newGamePad2.right_trigger.state) {
                Intake.fullPower();
            } else if (newGamePad2.left_trigger.state) {
                Intake.fullReverse();
            } else {
                Intake.stop();
            }

            if (newGamePad2.y.state) {
                Launch.hiveLaunch(false);
            } else if (newGamePad2.a.state) {
                Launch.flowerLaunch(false);
            } else {
                Launch.stop();
            }

            telemetry.update();
        }
    }
}
