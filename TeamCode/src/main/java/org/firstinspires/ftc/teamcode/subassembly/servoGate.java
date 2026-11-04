package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoGate {

    private Servo gateS = null;

    private LinearOpMode opmode = null;

    public servoGate(){}

    public void init(LinearOpMode opMode){
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        gateS = hwMap.servo.get("gateS");
        gateS.setPosition(0);
    }
    public void right(){
        gateS.setDirection(Servo.Direction.FORWARD);
    }
    public void left(){
        gateS.setDirection(Servo.Direction.REVERSE);
    }
    public void postion(int pos){
        gateS.setPosition(pos);
    }
    public void stop(){
        gateS.setPosition(0);
    }
}
