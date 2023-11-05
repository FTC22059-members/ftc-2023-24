package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.IntakeCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.processors.TeamPropVisionProcessor;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;
import org.firstinspires.ftc.teamcode.subsystems.WebcamSub;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "Autonomous Red 1 Stage")
public class AutoRed1Stage extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;
    private WebcamSub webcam;
    private IntakeSub intake;
    private PixelDropperSub pixelDropper;
    private TeamPropVisionProcessor teamPropVisionProcessor;
    private VisionPortal teamPropVisionPortal;


    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initializing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        webcam = new WebcamSub(hardwareMap, telemetry);
        intake = new IntakeSub(hardwareMap, telemetry);
        pixelDropper = new PixelDropperSub(hardwareMap, telemetry);

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
                    , new EjectCmd(intake)
                    , turnCW(75)
                    , drive(24)
                    , turnCCW(90)
                    , drive(16)
                    , new IntakeCmd(intake)
                    , turnCCW(180)
                    , drive(72)
                    , turnCW(45)
                    , drive(12)
                    , new DriveAprilTagCmd(4, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
                    , new InstantCommand(() -> {aprilTagVisionPortal.close();})
            ));
        }else if (branch == TeamPropVisionProcessor.Selected.MIDDLE) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , new EjectCmd(intake)
                    , turnCCW(90)
                    , new DriveAprilTagCmd(8, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , new IntakeCmd(intake)
                    , turnCW(90)
                    , drive(30)
                    , turnCW(90)
                    , drive(72)
                    , turnCW(25)
                    , drive(12)
                    , new DriveAprilTagCmd(5, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
                    , new InstantCommand(() -> {aprilTagVisionPortal.close();})
            ));
        } else if (branch == TeamPropVisionProcessor.Selected.RIGHT) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(75)
                    , new EjectCmd(intake)
                    , turnCCW(165)
                    , new DriveAprilTagCmd(8, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , new IntakeCmd(intake)
                    , turnCW(97)
                    , drive(30)
                    , turnCW(90)
                    , drive(72)
                    , turnCW(25)
                    , drive(12)
                    , new DriveAprilTagCmd(6, aprilTagVisionPortal.getVisionProcessor(), drive, telemetry)
                    , turnCW(180)
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
