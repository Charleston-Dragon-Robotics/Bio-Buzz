package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GamepadStates;

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

        telemetry.addLine("press X for blue, press B for red 'w'");
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
        telemetry.addLine("press X for left, press B for right");
        telemetry.update();

        while (!locationSelected) {
            if (gamepad1.bWasReleased()) {
                right = true;
                locationSelected = true;
            } else {
                if (gamepad1.xWasReleased()) {
                    left = true;
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
                    telemetry.addLine("auto started waow :3");
                    telemetry.update();
                    telemetryFix = true;
                }
            }
        }
    }
}
// ju9tujtsj0utsj0u sj0isyr0jyrdsji0ysrjirysijyrsjpiyrsi0jrysji0yrsijjirys-ysrpijrysk-dyr-irdyko-rdyk-oyrdkoul-ydrk-oydrkojillililj
// telemetry.addLine("My name is Gino.");
// telemetry.addLine("My name is Gino and I don't larp ANYTHING.");