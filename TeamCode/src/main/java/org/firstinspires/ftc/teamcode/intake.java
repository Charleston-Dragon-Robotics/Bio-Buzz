package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {
    private DcMotor IntakeM = null;

    private LinearOpMode opmode = null;
    public intake(){

    }

    public void init(LinearOpMode opMode, boolean Debug){
        if (Debug) {
            opmode.telemetry.addLine("intake init start");
        }

        HardwareMap hwMap;

        opmode = opMode;
        hwMap = opMode.hardwareMap;

        IntakeM = hwMap.dcMotor.get("IntakeM");

        IntakeM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        if (Debug){
            opmode.telemetry.addLine("intake init complete");
        }
        opmode.telemetry.update();

    }
    public void IntakeTest(double speed, boolean Debug){
        IntakeM.setPower(speed);
        if (Debug){
            opmode.telemetry.addData("motor speed", speed);
        }
    }
}
