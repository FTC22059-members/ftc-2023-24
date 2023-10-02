package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

@Autonomous(name = "Autonomous Red 1 Stage")
public class AutoRed1 extends CommandOpMode
{
    private DrivetrainSub drive;
    private DriveDistanceCmd driveHalfTileLength;
    private DriveDistanceCmd driveTileLength;
    private DriveDistanceCmd drive3TileLength;

    private TurnCmd turnCmd10CW;
    private TurnCmd turnCmd75CW;
    private TurnCmd turnCmd75CCW;
    private TurnCmd turnCmd90CCW;
    private TurnCmd turnCmd165CW;
    private TurnCmd turnCmd180;

    private ImuSub imu;

    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);

        //Setting up Cmds
        driveHalfTileLength = new DriveDistanceCmd(12, 0.6, drive, telemetry);
        driveTileLength = new DriveDistanceCmd(24, 0.6, drive, telemetry);
        drive3TileLength = new DriveDistanceCmd(72, 0.6, drive, telemetry);
        turnCmd10CW = new TurnCmd(180,0.6,drive,imu,telemetry);
        turnCmd75CW = new TurnCmd(75,0.2,drive,imu,telemetry);
        turnCmd75CCW = new TurnCmd(-75,0.2,drive,imu,telemetry);
        turnCmd90CCW = new TurnCmd(-90,0.6,drive,imu,telemetry);
        turnCmd165CW = new TurnCmd(165,0.6,drive,imu,telemetry);
        turnCmd180 = new TurnCmd(180,0.6,drive,imu,telemetry);


        waitForStart();
        schedule(new SequentialCommandGroup(
                driveTileLength,
                turnCmd75CCW
                /**turnCmd75CW,
                driveTileLength,
                turnCmd90CCW,
                driveTileLength,
                turnCmd180,
                drive3TileLength,
                turnCmd10CW,
                driveHalfTileLength**/
                ));

    }
}
