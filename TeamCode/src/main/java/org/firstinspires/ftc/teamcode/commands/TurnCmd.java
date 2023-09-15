package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;

public class TurnCmd extends CommandBase {

    private final DrivetrainSub drivetrainSub;
    private final double turnAngle;
    private final double turnSpeed;
    private Telemetry telemetry;
    private ImuSub imu;

    public TurnCmd(double p_turnAngle, double p_turnSpeed, DrivetrainSub p_drivetrainSub, ImuSub p_imu, Telemetry p_telemetry) {
        turnAngle = p_turnAngle;
        turnSpeed = p_turnSpeed;
        drivetrainSub = p_drivetrainSub;
        imu = p_imu;
        telemetry = p_telemetry;

        addRequirements(p_drivetrainSub);
    }

    @Override
    public void initialize() {
        drivetrainSub.resetEncoders();
        drivetrainSub.move(0, turnSpeed);
        imu.resetAngle();
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSub.getDrive().stop();
    }


    @Override
    public boolean isFinished() {
        System.out.println("IMU Angle: " + imu.getAngle());
        System.out.println("Is done? "+ (Math.abs(imu.getAngle()) >= turnAngle));
        return Math.abs(imu.getAngle()) >= turnAngle;
    }

}
