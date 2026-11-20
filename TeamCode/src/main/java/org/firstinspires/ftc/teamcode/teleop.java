package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.os.Debug;
import android.view.View;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
//import com.bylazar.

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.GamepadStates;

import java.util.List;

// this too


@TeleOp(name = "Teleop", group = "Teleop")
// this is the thing that we run
public class teleop extends LinearOpMode {

    View relativeLayout;


    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {

        Boolean Debug = Boolean.TRUE;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // declare subassembly classes
        driveTrain Train = new driveTrain();
        Intake Intake = new Intake();
        Launcher Launcher = new Launcher();
        flowerServo Flow = new flowerServo();
        CRServo turnTableServo1 = null;

        // initialize subassembly classes
        Train.init(this, Debug);
        Intake.init(this);
        Launcher.init(this, Debug);
        Flow.init(this);

        //limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);
//        limelight.start();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

//        LL.getStatus();

        double speed = 0.5;

        if (Debug == Boolean.TRUE){telemetry.addLine("Telop initalized");}

        waitForStart();

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();


            if(newGamePad1.left_trigger.state) {
                Train.multi(-gamepad1.left_stick_y/2, gamepad1.left_stick_x/2, gamepad1.right_stick_x/2, Debug);
            } else {
                Train.multi(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, Debug);
            }

            if(newGamePad1.dpad_up.state) {
                Intake.control(0.75);
            } else if (newGamePad1.dpad_down.state) {
                Intake.control(-0.75);
            } else {
                Intake.stop();
            }

            if(newGamePad1.left_bumper.state) {
                Launcher.turnTableRotation(-1, true);
            } else if(newGamePad1.right_bumper.state) {
                Launcher.turnTableRotation(1, true);
            } else{
                Launcher.turnTableRotation(0, true);
            }

            if (newGamePad1.guide.state) {
                Flow.setDown();
            } else if(newGamePad1.left_stick_button.state) {
                Flow.setUp();
            } else{
                Flow.stop();
            }

            //initialize speed as a variable
            telemetry.addData("Speed: ", speed);
            telemetry.update();
        }
    }
}