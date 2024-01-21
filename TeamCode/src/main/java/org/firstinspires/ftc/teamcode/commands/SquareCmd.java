package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.WallDistanceSub;

/**
 * This command is dedicated to squaring a robot to a wall
 */

public class SquareCmd extends CommandBase {

    private final DrivetrainSub drivetrainSub;
    private Telemetry telemetry;
    private double DistanceL;
    private double DistanceR;
    private WallDistanceSub wallDistanceSub;

    /**
     * Turns at a certain angle and speed importing the IMU and telemetry.
     *
     * @param drivetrainSubParam
     * @param telemetryParam
     */

    public SquareCmd(DrivetrainSub drivetrainSubParam, Telemetry telemetryParam, WallDistanceSub wallDistanceSubParam) {
        drivetrainSub = drivetrainSubParam;
        telemetry = telemetryParam;
        wallDistanceSub = wallDistanceSubParam;

        addRequirements(drivetrainSubParam);
        addRequirements(wallDistanceSubParam);
    }

    @Override
    public void execute(){
        DistanceL = wallDistanceSub.getDistance(1);
        DistanceR = wallDistanceSub.getDistance(2);
        telemetry.addData("diff of L+R: ",String.valueOf(DistanceL-DistanceR));

        if (DistanceL-DistanceR>=1) {
            drivetrainSub.move(0, 0.1);
        }else if (DistanceL-DistanceR<=-1) {
            drivetrainSub.move(0, -0.1);
        }
    }


    @Override
    public void end(boolean interrupted) {
        drivetrainSub.getDrive().stop();
    }


    @Override
    public boolean isFinished() {
            if (Math.abs(DistanceL-DistanceR)>=1) {
                drivetrainSub.move(0, 0);
                System.out.println("I made it to the true branch of isFinished!");
                return true;
            }else{
                System.out.println("I made it to the false branch of isFinished!");
                return false;
            }
    }

}