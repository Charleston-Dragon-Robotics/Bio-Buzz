//package org.firstinspires.ftc.teamcode.Autonumis;
//
//import static com.pedropathing.api.Paths.line;
//import static com.pedropathing.ivy.Scheduler.schedule;
//import static com.pedropathing.ivy.commands.Commands.instant;
//import static com.pedropathing.ivy.commands.Commands.waitMs;
//import static com.pedropathing.ivy.groups.Groups.parallel;
//import static com.pedropathing.ivy.groups.Groups.sequential;
//import static com.pedropathing.ivy.pedro.PedroCommands.follow;
//
//import com.pedropathing.api.Paths;
//import com.pedropathing.api.PoseFactory;
//import com.pedropathing.follower.Follower;
//import com.pedropathing.ivy.Command;
//import com.pedropathing.ivy.Scheduler;
//import com.pedropathing.math.Pose;
//import com.pedropathing.paths.Path;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.subassemblies.*;
//
//
//@Autonomous(name = "autoBlueFar", group = "Autonomous")
//public class autoBlueRight extends LinearOpMode {
//
//    Launcher launch = new Launcher();
//    flowerServo Flow = new flowerServo();
//    Intake intake = new Intake();
//    servoGate servoGate = new servoGate();
//
//
//    private final PoseFactory poseFactory = PoseFactory.degrees();
//
//
//    public final Pose startBlueRight = poseFactory.of(83.5188, 133.5213, -90);
//    private final Pose launchFlower = poseFactory.of(135.5383, 94.766, 0);
//    private final Pose flowerLaunchStart = poseFactory.of(135.5383, 94.766, 0);
//    private final Pose flowerLaunch = poseFactory.of(84.6532, 22.3029, 90);
//    private final Pose flowerLaunchControl1 = poseFactory.of(138.4403, 30.3363, 0);
//    private final Pose launchFlower2Start = poseFactory.of(84.6532, 22.3029, -90);
//    private final Pose launchFlower2 = poseFactory.of(94.5991, 3.5844, -90);
//    private final Pose flowerLaunch2Start = poseFactory.of(94.5991, 3.5844, 0);
//    private final Pose flowerLaunch2 = poseFactory.of(80.0097, 22.2789, 90);
//    private final Pose launchParkStart = poseFactory.of(82.6558, 28.512, 0);
//    private final Pose launchPark = poseFactory.of(136.4274, 31.2004, 0);
//
//
//    public Path launchToFlowerABFar() {
//        return Paths.line(startBlueRight, launchFlower).linear(startBlueRight, launchFlower);
//    }
//
//    public Path flowerToLaunch() {
//        return Paths.curve(flowerLaunchStart, flowerLaunchControl1, flowerLaunch).linear(flowerLaunchStart, flowerLaunch);
//    }
//
//    public Path launchToFlower2() {
//        return Paths.line(launchFlower2Start, launchFlower2).linear(launchFlower2Start, launchFlower2);
//    }
//
//    public Path flowerToLaunch2() {
//        return Paths.line(flowerLaunch2Start, flowerLaunch2).linear(flowerLaunch2Start, flowerLaunch2);
//    }
//
//    public Path launchToPark() {
//        return Paths.line(launchParkStart, launchPark).linear(launchParkStart, launchPark);
//    }
//
//
//    public Command autoBlueRight() {
//        return sequential(
//                instant(() -> servoGate.Active()),
//                instant(() -> launch.PHiveLaunch()),
//                instant(() -> servoGate.Hold()),
//                parallel(
//                        follow(follower, launchToFlowerABFar()),
//                        waitMs(1000),
//                        instant(() -> Flow.setDown()),
//                        instant(() -> intake.fullPower()),
//                        waitMs(3000)),
//                parallel(
//                        instant(() -> intake.stop()),
//                        follow(follower, flowerToLaunch())),
//                parallel(
//                        instant(() -> servoGate.Active()),
//                        instant(() -> intake.fullPower()),
//                        instant(() -> Flow.setUp())),
//                instant(() -> launch.PHiveLaunch()),
//                instant(() -> intake.stop()),
//                parallel(
//                        follow(follower, launchToFlower2()),
//                        waitMs(2000),
//                        instant(() -> Flow.setDown()),
//                        instant(() -> intake.fullPower())),
//                        waitMs(3000),
//                follow(follower, flowerToLaunch2()),
//                parallel(
//                        instant(() -> servoGate.Active()),
//                        instant(() -> )
//                )
//                parallel(
//                        instant(() -> intake.stop()),
//                follow(follower, launchToPark())
//        );
//    }
//
//    // if this doesnt work make this static
//    public Follower follower;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        launch.init(this, false);
//        Flow.init(this);
//        intake.init(this);
//        servoGate.init(this);
//
//        Scheduler.reset();
//
////        follower = Constants.create(hardwareMap);
////        follower.setPose(startBlueFar);
////        instant(() -> launch.hiveLaunch(true));
////        schedule(follow(follower, launchToFlowerABFar()));
////        instant(() -> Flow.setDown());
////        schedule(follow(follower, flowerToLaunch()));
////        instant(() -> Flow.setUp());
////        instant(() -> launch.hiveLaunch(true));
////        schedule(follow(follower, launchToFlower2()));
////        schedule(follow(follower, flowerToLaunch2()));
////        instant(() -> launch.hiveLaunch(true));
////        schedule(follow(follower, launchToPark()));
//
//        schedule(autoBlueRight());
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            follower.update();
//            Scheduler.execute();
//
//            telemetry.addData("X", follower.pose().x());
//            telemetry.addData("Y", follower.pose().y());
//            telemetry.update();
//
//        }
//    }
//}
