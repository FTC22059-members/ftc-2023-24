package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;

@Autonomous(name = "Autonomous Blue 1 Stage")
public class AutoBlue1Stage extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private DrivetrainSub drive;
    private ImuSub imu;

    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);


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
                    , turnCW(165)
                    , new DriveAprilTagCmd(10, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCCW(82)
                    , drive(30)
                    , turnCCW(85)
                    , drive(60)
                    , turnCCW(35)
                    , drive(18)
                    , new DriveAprilTagCmd(1, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
            ));
        }else if (branch == "C") {
            schedule(new SequentialCommandGroup(
                    drive(24)
                    , turnCW(90)
                    , new DriveAprilTagCmd(10, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
                    , turnCCW(82)
                    , drive(30)
                    , turnCCW(85)
                    , drive(72)
                    , turnCCW(25)
                    , drive(12)
                    , new DriveAprilTagCmd(2, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
            ));
        } else if (branch == "R") {schedule(new SequentialCommandGroup(
                drive(24)
                , turnCW(75)
                , turnCCW(70)
                , drive(24)
                , turnCW(90)
                , drive(16)
                , turnCW(180)
                , drive(72)
                , turnCCW(30)
                , drive(12)
                , new DriveAprilTagCmd(3, hardwareMap.get(WebcamName.class, "Webcam 1"), drive, telemetry)
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
