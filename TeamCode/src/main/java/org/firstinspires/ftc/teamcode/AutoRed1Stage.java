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
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.PixelDropperSub;

@Autonomous(name = "Autonomous Red 1 Stage")
public class AutoRed1Stage extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;
    private IntakeSub intake;
    private PixelDropperSub pixelDropper;

    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        intake = new IntakeSub(hardwareMap, telemetry);
        pixelDropper = new PixelDropperSub(hardwareMap, telemetry);


        //Find the position of team goal
        String branch = "N";

        while(branch == "N"){
            if (gamepad1.x){
                branch = "L";
            }else if(gamepad1.y){
                branch = "C";
            }else if(gamepad1.b){
                branch = "R";
            }
        }
        
        waitForStart();
        if (branch == "L") {
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
                    , new DriveAprilTagCmd(4, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
            ));
        }else if (branch == "C") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , new EjectCmd(intake)
                    , turnCCW(90)
                    , new DriveAprilTagCmd(8, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , new IntakeCmd(intake)
                    , turnCW(90)
                    , drive(30)
                    , turnCW(90)
                    , drive(72)
                    , turnCW(25)
                    , drive(12)
                    , new DriveAprilTagCmd(5, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
            ));
        } else if (branch == "R") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(75)
                    , new EjectCmd(intake)
                    , turnCCW(165)
                    , new DriveAprilTagCmd(8, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , new IntakeCmd(intake)
                    , turnCW(97)
                    , drive(30)
                    , turnCW(90)
                    , drive(72)
                    , turnCW(25)
                    , drive(12)
                    , new DriveAprilTagCmd(6, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(180)
                    , new PixelDropperCmd(pixelDropper)
                    , new PixelDropperCmd(pixelDropper)
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
