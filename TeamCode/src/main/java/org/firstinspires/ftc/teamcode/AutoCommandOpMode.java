package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.ArmDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.processors.TeamPropVisionProcessor;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.OldIntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;
import org.firstinspires.ftc.teamcode.subsystems.WebcamSub;
import org.firstinspires.ftc.vision.VisionPortal;

public class AutoCommandOpMode extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;
    private WebcamSub webcam;
    protected OldIntakeSub intake;
    protected PixelDropperSub pixelDropper;

    protected ArmDistanceCmd armDown;
    protected ArmDistanceCmd armNeutral;
    protected ArmDistanceCmd armUp;

    private TeamPropVisionProcessor teamPropVisionProcessor;
    private VisionPortal teamPropVisionPortal;

    private ArmSub arm;

    private boolean fieldCentric = true;
    @Override
    public void initialize() {
        //Initializing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        webcam = new WebcamSub(hardwareMap, telemetry);
        intake = new OldIntakeSub(hardwareMap, telemetry);
        pixelDropper = new PixelDropperSub(hardwareMap, telemetry);
        arm = new ArmSub(hardwareMap, telemetry);
        arm.resetEncoder();

        armDown = new ArmDistanceCmd(arm,telemetry,-0.5,1000);
        armNeutral = new ArmDistanceCmd(arm,telemetry,0.5,850);
        armUp = new ArmDistanceCmd(arm,telemetry,0.5,0);
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

            //  #     ##### ##### #####
            //  #     #     #       #
            //  #     ##### ####    #
            //  #     #     #       #
            //  ##### ##### #       #

            leftLogic();

            //   ###  ##### #    # ##### ##### ####
            //  #     #     ##   #   #   #     #   #
            //  #     ##### # #  #   #   ##### ####
            //  #     #     #  # #   #   #     #   #
            //   ###  ##### #   ##   #   ##### #   #

        }else if (branch == TeamPropVisionProcessor.Selected.MIDDLE) {
            centerLogic();

            //  ###   #  ###  #   # #####
            //  #  #  # #     #   #   #
            //  ###   # #  ## #####   #
            //  #  #  # #   # #   #   #
            //  #  #  #  ###  #   #   #

        } else if (branch == TeamPropVisionProcessor.Selected.RIGHT) {
            rightLogic();
        }
        schedule(new SequentialCommandGroup(new InstantCommand(() -> {
            aprilTagVisionPortal.close();
        })));
    }

    public void leftLogic(){}

    public void centerLogic(){}

    public void rightLogic(){}

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