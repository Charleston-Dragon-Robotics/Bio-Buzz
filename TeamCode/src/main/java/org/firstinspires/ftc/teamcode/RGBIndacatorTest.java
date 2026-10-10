package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.GamepadStates;

@TeleOp(name = "RGBIndacatorTest", group = "Teleop")
public class RGBIndacatorTest extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        Boolean Debug = Boolean.TRUE;

        Lights Light = new Lights();

        Light.init(this, Debug);

        GamepadStates newGamePad1 = new GamepadStates(gamepad1);
        waitForStart();

        while (opModeIsActive()){

            if(newGamePad1.a.state){
                Light.colorControl("green",Debug);
            } else if (newGamePad1.b.state) {
                Light.colorControl("red",Debug);
            }else if (newGamePad1.x.state) {
                Light.colorControl("blue",Debug);
            }else if (newGamePad1.y.state) {
                Light.colorControl("yellow",Debug);
            }

            newGamePad1.updateState();

        }
    }
}
