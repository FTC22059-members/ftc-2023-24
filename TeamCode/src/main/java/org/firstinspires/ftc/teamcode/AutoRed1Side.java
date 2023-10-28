package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.EjectCmd;
import org.firstinspires.ftc.teamcode.commands.IntakeCmd;
import org.firstinspires.ftc.teamcode.commands.PixelDropperCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "Autonomous Red 1 Side")
public class AutoRed1Side extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;
    private IntakeSub intake;
    private PixelDropperSub pixelDropper;

    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;


    private boolean fieldCentric = true;
    @Override
    public void initialize() {
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        intake = new IntakeSub(hardwareMap, telemetry);
        pixelDropper = new PixelDropperSub(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);

        //Find the position of team goal
        FirstVisionProcessor.Selected branch = FirstVisionProcessor.Selected.NONE;

        while(opModeInInit()){
            branch = visionProcessor.getSelection();
            telemetry.addData("Branch = ", branch.toString());
            telemetry.update();
        }

        waitForStart();

        visionPortal.close();

        if (branch == FirstVisionProcessor.Selected.LEFT) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCCW(75)
                    , new EjectCmd(intake)
                    , turnCW(75)
                    , drive(23)
                    , turnCCW(85)
                    , drive(18)
                    , new IntakeCmd(intake)
                    , turnCCW(85)
                    , drive(50)
                    , turnCCW(81)
                    , drive(65)
                    , turnCCW(30)
                    , drive(18)
                    , new DriveAprilTagCmd(4, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
            ));
        }else if (branch == FirstVisionProcessor.Selected.MIDDLE) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , new EjectCmd(intake)
                    , turnCCW(90)
                    , new DriveAprilTagCmd(8, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , new IntakeCmd(intake)
                    , turnCCW(78)
                    , drive(15)
                    , turnCCW(83)
                    , drive(60)
                    , turnCCW(20)
                    , drive(12)
                    , new DriveAprilTagCmd(5, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
            ));
        } else if (branch == FirstVisionProcessor.Selected.RIGHT) {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(75)
                    , new EjectCmd(intake)
                    , turnCCW(165)
                    , new DriveAprilTagCmd(8, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , new IntakeCmd(intake)
                    , turnCCW(76)
                    , drive(15)
                    , turnCCW(83)
                    , drive(60)
                    , turnCCW(20)
                    , drive(12)
                    , new DriveAprilTagCmd(6, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
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