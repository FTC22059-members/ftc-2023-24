package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

@Autonomous(name = "Autonomous 2023-24")
public class Autonomous24 extends CommandOpMode {
    private DrivetrainSub drive;
    private DriveDistanceCmd driveDistanceCmd;
    private TurnCmd turnCmd;
    private ImuSub imu;

    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        //Initalizing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);

        //Setting up Cmds
        driveDistanceCmd = new DriveDistanceCmd(24, 0.6, drive, telemetry);
        turnCmd = new TurnCmd(80,0.6,drive,imu,telemetry);

        waitForStart();
        //driving in a square
        schedule(new SequentialCommandGroup(driveDistanceCmd, turnCmd,
                                            driveDistanceCmd, turnCmd,
                                            driveDistanceCmd, turnCmd,
                                            driveDistanceCmd, turnCmd));
    }
}
