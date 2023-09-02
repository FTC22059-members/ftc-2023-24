package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

@Autonomous(name = "Autonomous 2023-24")
public class Autonomous24 extends CommandOpMode {
    private DrivetrainSub drive;
    private DriveDistanceCmd driveCmd;
    private boolean fieldCentric = true;
    @Override
    public void initialize(){
        drive = new DrivetrainSub(hardwareMap, telemetry);
        driveCmd = new DriveDistanceCmd(5, 0.6, drive, telemetry);
        driveCmd.initialize();

        register(drive);
        drive.setDefaultCommand(driveCmd);
    }

    @Override
    public void run(){

    }
}
