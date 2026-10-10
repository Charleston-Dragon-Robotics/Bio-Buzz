package org.firstinspires.ftc.teamcode;

import android.graphics.ColorSpace;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class Lights {

    private Servo rgb = null;

    private LinearOpMode opmode = null;

    public void init(LinearOpMode opMode, Boolean Debug) {
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opmode.hardwareMap;

        rgb = hwMap.servo.get("RGB");

        rgb.setPosition(0);

        if(Debug == Boolean.TRUE){opMode.telemetry.addLine("Lights initialized");}
        opMode.telemetry.update();
    }

    public void colorControl(String color, boolean Debug){

        String newColor = color.toLowerCase();

        if(Debug == Boolean.TRUE){opmode.telemetry.addData("    ---colorControl---", newColor);}
        if(newColor != "red" || newColor != "yellow" || newColor != "green"|| newColor != "blue" ){opmode.telemetry.addLine("    ---colorControl---   INVALID COLOR" );}


        switch (newColor ) {

            case("red"):
                rgb.setPosition(0.279);
                if(Debug == Boolean.TRUE){opmode.telemetry.addLine("Light Red");}
                break;
            case("yellow"):
                rgb.setPosition(0.380);
                if(Debug == Boolean.TRUE){opmode.telemetry.addLine("Light Yellow");}
                break;
            case("green"):
                rgb.setPosition(0.500);
                if(Debug == Boolean.TRUE){opmode.telemetry.addLine("Light Green");}
                break;
            case("blue"):
                rgb.setPosition(0.611);
                if(Debug == Boolean.TRUE){opmode.telemetry.addLine("Light Blue");}
                break;
        }
           opmode.telemetry.update();
    }

}
