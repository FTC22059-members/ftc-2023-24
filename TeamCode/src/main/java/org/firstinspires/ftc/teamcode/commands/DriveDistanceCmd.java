package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

/**
 * This command is dedicated to driving forwards/backwards a certain amount of distance and speed
 */

public class DriveDistanceCmd extends CommandBase {

    private final DrivetrainSub drivetrainSub;
    private final double driveDistance;
    private final double driveSpeed;
    private Telemetry telemetry;

    /**
     * Drives the robot forward/backwards for a certain distance and speed.
     *
     * @param dd The number of inches the robot will drive
     * @param ds  The speed at which the robot will drive
     * @param dts  The drive subsystem on which this command will run
     * @param tm Telemetry input
     */

    public DriveDistanceCmd(double dd, double ds, DrivetrainSub dts, Telemetry tm) {
        driveDistance = dd;
        driveSpeed = ds;
        drivetrainSub = dts;
        telemetry = tm;
        addRequirements(dts);
    }

    @Override
    public void initialize() {
        System.out.println("Drive Distance Initialized");
        drivetrainSub.resetEncoders();
        if (driveDistance > 0){
            drivetrainSub.move(driveSpeed, 0);
        }else{
            drivetrainSub.move(-driveSpeed, 0);
        }

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("End Called");
        drivetrainSub.getDrive().stop();
        //drivetrainSub.move(0, 0);
    }


    @Override
    public boolean isFinished() {
        System.out.println("Encoder Distance: " + drivetrainSub.getFrontLeftEncoderDistance());
        System.out.println("Is done? "+ (Math.abs(drivetrainSub.getFrontLeftEncoderDistance()) >= Math.abs(driveDistance)));
        return Math.abs(drivetrainSub.getFrontLeftEncoderDistance()) >= Math.abs(driveDistance);
    }

}
