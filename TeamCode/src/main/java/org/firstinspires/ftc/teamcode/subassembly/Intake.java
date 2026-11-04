package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor IntakeM = null;

    private LinearOpMode opmode = null;

    public Intake(){}

    public void init(LinearOpMode opMode){
        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        IntakeM = hwMap.dcMotor.get("IntakeM");


        IntakeM.setDirection(DcMotorSimple.Direction.FORWARD);

        IntakeM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        IntakeM.setPower(0);

    }
    public void fullPower(){
        IntakeM.setPower(1);
    }
    public void control(int power){
        IntakeM.setPower(power);
    }
    public void fullRevers(){
        IntakeM.setPower(-1);
    }
    public void stop(){
        IntakeM.setPower(0);
    }
}
