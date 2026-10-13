package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class newIntake {

    private DcMotor IntakeM = null;
    private LinearOpMode opmode = null;


    public void init(LinearOpMode opMode) {

        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        //names the motor
        IntakeM = hwMap.dcMotor.get("IntakeM");


        // sets the motors direction
        IntakeM.setDirection(DcMotorSimple.Direction.REVERSE);


        // sets power to 0 to make sure nothing moves at first
        IntakeM.setPower(0);


    }

    //creates intake functions
    public void intake(double speed) {
        // when intake is called, moves the motor forward with power value 1
        IntakeM.setPower(speed);
    }

    public void reverseIntake(double speed){
        // reverses intake
        IntakeM.setPower(-speed);
    }
    public void stop() {
        // stops the motor because yes obviously it does
        IntakeM.setPower(0);
    }
}