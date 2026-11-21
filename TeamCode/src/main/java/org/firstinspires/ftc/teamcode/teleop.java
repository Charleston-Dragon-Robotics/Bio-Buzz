package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
//import com.bylazar.


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
        servoGate servoGate = new servoGate();

        // initialize subassembly classes
        Train.init(this, Debug);
        Intake.init(this);
        Launcher.init(this, Debug);
        Flow.init(this);
        servoGate.init(this);

        //limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        telemetry.setMsTransmissionInterval(11);
//        limelight.pipelineSwitch(7);
//        limelight.start();

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        GamepadStates newGamePad2 = new GamepadStates(gamepad2);

//        LL.getStatus();

        double speed = 0.5;

        if (Debug == Boolean.TRUE){telemetry.addLine("Teleop initialized");}

        waitForStart();

        while (opModeIsActive()) {

            newGamePad1.updateState();
            newGamePad2.updateState();


            if(newGamePad1.left_trigger.state) {
                Train.multi(-gamepad1.left_stick_y/2, gamepad1.left_stick_x/2, gamepad1.right_stick_x/2, Debug);
            } else {
                Train.multi(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, Debug);
            }

            if(newGamePad2.dpad_up.state) {
                Intake.control(0.75);
            } else if (newGamePad2.dpad_down.state) {
                Intake.control(-0.75);
            } else {
                Intake.stop();
            }

            if(newGamePad2.left_bumper.state) {
                Launcher.turnTableRotation(-1, true);
            } else if(newGamePad2.right_bumper.state) {
                Launcher.turnTableRotation(1, true);
            } else{
                Launcher.turnTableRotation(0, true);
            }

            if (newGamePad2.guide.state) {
                Flow.setDown();
            } else if(newGamePad2.left_stick_button.state) {
                Flow.setUp();
            }

            if (newGamePad2.dpad_right.state) {
                servoGate.Active();
            }else if(newGamePad2.dpad_left.state) {
                servoGate.Hold();
            }

            //initialize speed as a variable
            telemetry.addData("Speed: ", speed);
            telemetry.update();
        }
    }
}