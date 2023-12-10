package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.processors.TeamPropVisionProcessor;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;
import org.firstinspires.ftc.teamcode.subsystems.WebcamSub;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "Autonomous Red 2")
public class AutoRed2 extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;
    private WebcamSub webcam;
    private IntakeSub intake;
    private PixelDropperSub pixelDropper;
    private ArmSub arm;

    private TeamPropVisionProcessor teamPropVisionProcessor;
    private VisionPortal teamPropVisionPortal;


    private boolean fieldCentric = true;
    @Override
    public void initialize() {
        //Initializing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        webcam = new WebcamSub(hardwareMap, telemetry);
        intake = new IntakeSub(hardwareMap, telemetry);
        pixelDropper = new PixelDropperSub(hardwareMap, telemetry);
        arm = new ArmSub(hardwareMap, telemetry);

        ArmDistanceCmd armDown = new ArmDistanceCmd(arm,telemetry,-0.5,1000);
        ArmDistanceCmd armNeutral = new ArmDistanceCmd(arm,telemetry,0.5,850);
        ArmDistanceCmd armUp = new ArmDistanceCmd(arm,telemetry,0.5,0);

        teamPropVisionProcessor = new TeamPropVisionProcessor();
        teamPropVisionPortal = VisionPortal.easyCreateWithDefaults(webcam.getWebcamName(), teamPropVisionProcessor);

        //Find the position of team goal
        TeamPropVisionProcessor.Selected branch = TeamPropVisionProcessor.Selected.NONE;

        while(opModeInInit()){
            branch = teamPropVisionProcessor.getSelection();
            telemetry.addData("Branch = ", branch.toString());
            telemetry.update();
        }

        teamPropVisionPortal.close();

        waitForStart();

        //Set up vision processor
        AprilTagVisionPortal aprilTagVisionPortal = new AprilTagVisionPortal(webcam.getWebcamName(), telemetry);
        aprilTagVisionPortal.initialize();

        if (branch == TeamPropVisionProcessor.Selected.LEFT) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCCW(75)
                    , armDown
                    , new EjectCmd(intake)
                    , armUp
                    , turnCCW(15)
                    , drive(-30)
                    , new DriveAprilTagCmd(4, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
                    , new InstantCommand(() -> {aprilTagVisionPortal.close();})
            ));
        }else if (branch == TeamPropVisionProcessor.Selected.MIDDLE) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , armDown
                    , drive(6)
//                    , drive(-4)
                    , new EjectCmd(intake)
                    , drive(-2)
                    , armUp
                    , turnCCW(90)
                    , drive(-35)
//                    , new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
                    , new InstantCommand(() -> {aprilTagVisionPortal.close();})
            ));
        } else if (branch == TeamPropVisionProcessor.Selected.RIGHT) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(75)
                    , armDown
                    , new EjectCmd(intake)
                    , armUp
                    , turnCCW(75)
                    , drive(-16)
                    , turnCW(90)
                    , drive(30)
                    , turnCCW(90)
                    , drive(14)
//                    , new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , turnCCW(90)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
                    , new InstantCommand(() -> {aprilTagVisionPortal.close();})
            ));
        }
    }

    public TurnCmd turnCW(int angle){
        return new TurnCmd(-angle,turnSpeed,drive,imu,telemetry);
    }

    public TurnCmd turnCCW(int angle){
        return new TurnCmd(angle,turnSpeed,drive,imu,telemetry);
    }

    public DriveDistanceCmd drive(int inches){
        return new DriveDistanceCmd(inches, driveSpeed, drive, telemetry);
    }
}