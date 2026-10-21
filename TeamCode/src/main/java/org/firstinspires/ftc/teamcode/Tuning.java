//
package org.firstinspires.ftc.teamcode;
//
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;

import org.firstinspires.ftc.teamcode.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.procedures.MecanumTuner;
import org.firstinspires.ftc.teamcode.procedures.PinpointTuner;
import org.firstinspires.ftc.teamcode.procedures.Tests;
//import com.pedropathing.ftc.drivetrains.MecanumConstants;

public class Tuning {
    @Tuner
    public static Procedure mecanumTuner() {
        return new MecanumTuner();
    }

//    @Tuner
//    public static Procedure pinpointTuner() {
//        return new PinpointTuner();
//    }
    // can do either first doesn't matter.
    // IS A FOUR BAR ODO THING JUDAH SAID.



//    @Tuner
//    public static Procedure foresightTuner() {
//        return new ForesightTuner((hardwareMap) -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig), (hardwareMap) -> new Mecanum(hardwareMap, Constants.drivetrainConfig));
//    }


    // Foresight must be done after Mecanum and Pinpoint is complete.
    // MOVE FIVE INCHES NO MATTER WHAT

}

