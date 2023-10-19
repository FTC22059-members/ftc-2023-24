package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.DriveAprilTagCmd;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

@Autonomous(name = "Autonomous Red 1 Stage")
public class AutoRed1 extends CommandOpMode
{
    private DrivetrainSub drive;
    private DriveDistanceCmd driveHalfTileLength;
    private DriveDistanceCmd drive20;
    private DriveDistanceCmd driveTileLength;
    private DriveDistanceCmd drive3TileLength;

    private TurnCmd turnCmd10CW;
    private TurnCmd turnCmd75CW;
    private TurnCmd turnCmd75CCW;
    private TurnCmd turnCmd90CCW;
    private TurnCmd turnCmd165CW;
    private TurnCmd turnCmd180;

    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    private ImuSub imu;

    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);


        //Setting up Cmds
        driveHalfTileLength = new DriveDistanceCmd(12, driveSpeed, drive, telemetry);
        drive20  = new DriveDistanceCmd(20, driveSpeed, drive, telemetry);
        driveTileLength = new DriveDistanceCmd(24, driveSpeed, drive, telemetry);
        drive3TileLength = new DriveDistanceCmd(72, driveSpeed, drive, telemetry);
        turnCmd10CW = new TurnCmd(-10,turnSpeed,drive,imu,telemetry);
        turnCmd75CW = new TurnCmd(-75,turnSpeed,drive,imu,telemetry);
        turnCmd75CCW = new TurnCmd(75,turnSpeed,drive,imu,telemetry);
        turnCmd90CCW = new TurnCmd(90,turnSpeed,drive,imu,telemetry);
        turnCmd165CW = new TurnCmd(-165,turnSpeed,drive,imu,telemetry);
        turnCmd180 = new TurnCmd(180,turnSpeed,drive,imu,telemetry);


        waitForStart();
        schedule(new SequentialCommandGroup(
                driveTileLength
                ,turnCmd75CCW
                ,turnCmd75CW
                ,driveTileLength
                ,turnCmd90CCW
                ,new DriveDistanceCmd(16, driveSpeed, drive, telemetry)
                ,turnCmd180
                ,drive3TileLength
                ,new TurnCmd(-45,turnSpeed,drive,imu,telemetry)
                ,driveHalfTileLength
                ,new DriveAprilTagCmd(5,hardwareMap.get(WebcamName.class, "Webcam 1"),drive,telemetry)
                ));

    }

    /**public TurnCmd turn(int angle){
        return new TurnCmd(angle,turnSpeed,drive,imu,telemetry);
    }*/
}
