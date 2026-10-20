package org.firstinspires.ftc.teamcode;
package org.firstinspires.ftc.teamcode.pedropathing;

import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;

import org.firstinspires.ftc.teamcode.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.procedures.MecanumTuner;
import com.pedropathing.ftc.drivetrains.MecanumConstants;

public class Procedure {
    @Tuner
    public static Procedure foresightTuner() {
        return new ForesightTuner((hardwareMap) -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig), (hardwareMap) -> new Mecanum(hardwareMap, Constants.drivetrainConfig));
    }
