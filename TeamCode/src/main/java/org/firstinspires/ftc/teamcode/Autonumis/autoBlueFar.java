package org.firstinspires.ftc.teamcode.Autonumis;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subassemblies.Intake;
import org.firstinspires.ftc.teamcode.subassemblies.Launcher;
import org.firstinspires.ftc.teamcode.subassemblies.flowerServo;

@Autonomous(name = "autoBlueFar", group = "Autonomous")
public class autoBlueFar extends LinearOpMode {

    Launcher launch = new Launcher();

    flowerServo Flow = new flowerServo();
    Intake intake = new Intake();


    private final PoseFactory poseFactory = PoseFactory.degrees();


    public final Pose startBlueFar = poseFactory.of(83.5188, 133.5213, -90);
    private final Pose launchFlower = poseFactory.of(135.5383, 94.766, 0);
    private final Pose flowerLaunchStart = poseFactory.of(135.5383, 94.766, 0);
    private final Pose flowerLaunch = poseFactory.of(84.6532, 22.3029, 90);
    private final Pose flowerLaunchControl1 = poseFactory.of(138.4403, 30.3363, 0);
    private final Pose launchFlower2Start = poseFactory.of(84.6532, 22.3029, -90);
    private final Pose launchFlower2 = poseFactory.of(94.5991, 3.5844, -90);
    private final Pose flowerLaunch2Start = poseFactory.of(94.5991, 3.5844, 0);
    private final Pose flowerLaunch2 = poseFactory.of(80.0097, 22.2789, 90);
    private final Pose launchParkStart = poseFactory.of(82.6558, 28.512, 0);
    private final Pose launchPark = poseFactory.of(136.4274, 31.2004, 0);


    public Path launchToFlowerABFar() {
        return Paths.line(startBlueFar, launchFlower).linear(startBlueFar, launchFlower);
    }

    public Path flowerToLaunch() {
        return Paths.curve(flowerLaunchStart, flowerLaunchControl1, flowerLaunch).linear(flowerLaunchStart, flowerLaunch);
    }

    public Path launchToFlower2() {
        return Paths.line(launchFlower2Start, launchFlower2).linear(launchFlower2Start, launchFlower2);
    }

    public Path flowerToLaunch2() {
        return Paths.line(flowerLaunch2Start, flowerLaunch2).linear(flowerLaunch2Start, flowerLaunch2);
    }

    public Path launchToPark() {
        return Paths.line(launchParkStart, launchPark).linear(launchParkStart, launchPark);
    }


    public Command autoBlueFar() {
        return sequential(
                instant(() -> launch.hiveLaunch(true)),
                follow(follower, launchToFlowerABFar()),
                instant(() -> Flow.setDown()),
                instant(() -> intake.fullPower()),
                follow(follower, flowerToLaunch()),
                instant(() -> launch.hiveLaunch(true)),
                instant(() -> Flow.setUp()),
                instant(() -> intake.stop()),
                follow(follower, launchToFlower2()),
                instant(() -> Flow.setDown()),
                instant(() -> intake.fullPower()),
                follow(follower, flowerToLaunch2()),
                instant(() -> intake.stop()),
                follow(follower, launchToPark())
        );
    }

    // if this doesnt work make this static
    public Follower follower;

    @Override
    public void runOpMode() throws InterruptedException {
        launch.init(this, false);
        Flow.init(this);
        intake.init(this);

        Scheduler.reset();

//        follower = Constants.create(hardwareMap);
//        follower.setPose(startBlueFar);
//        instant(() -> launch.hiveLaunch(true));
//        schedule(follow(follower, launchToFlowerABFar()));
//        instant(() -> Flow.setDown());
//        schedule(follow(follower, flowerToLaunch()));
//        instant(() -> Flow.setUp());
//        instant(() -> launch.hiveLaunch(true));
//        schedule(follow(follower, launchToFlower2()));
//        schedule(follow(follower, flowerToLaunch2()));
//        instant(() -> launch.hiveLaunch(true));
//        schedule(follow(follower, launchToPark()));

        schedule(autoBlueFar());

        waitForStart();

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("X", follower.pose().x());
            telemetry.addData("Y", follower.pose().y());
            telemetry.update();

        }
    }
}
