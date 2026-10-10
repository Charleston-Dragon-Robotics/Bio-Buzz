package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "hallEffectSensorText", group = "Teleop")
public class hallEffectSensorText extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        Boolean Debug = Boolean.TRUE;

        if (Debug){telemetry.addLine("hallEffectSensorText init start");}

        hallEffectSensor Sensor = new hallEffectSensor();

        Sensor.init(this, Debug);

        if (Debug){telemetry.addLine("hallEffectSensorText init start");}

        waitForStart();

        while (opModeIsActive()){

            telemetry.addData("Is the sensor touched",Sensor.isTouched(Debug));

        }
    }


}
