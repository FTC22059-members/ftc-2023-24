package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

@Autonomous(name = "Autonomous Red 1 Stage")
public class AutoRed1Stage extends CommandOpMode
{
    private DrivetrainSub drive;
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private ImuSub imu;

    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);


        //Find the position of team goal
        String branch = "R";


        waitForStart();
        if (branch == "L") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCCW(75)
                    , turnCW(75)
                    , drive(24)
                    , turnCCW(90)
                    , drive(16)
                    , turnCCW(180)
                    , drive(72)
                    , turnCW(45)
                    , drive(12)
                    , new DriveAprilTagCmd(4, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
            ));
        }else if (branch == "C") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCCW(90)
                    , new DriveAprilTagCmd(8, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(90)
                    , drive(30)
                    , turnCW(90)
                    , drive(72)
                    , turnCW(25)
                    , drive(12)
                    , new DriveAprilTagCmd(5, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
            ));
        } else if (branch == "R") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(75)
                    , turnCCW(165)
                    , new DriveAprilTagCmd(8, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCW(97)
                    , drive(30)
                    , turnCW(90)
                    , drive(72)
                    , turnCW(25)
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
