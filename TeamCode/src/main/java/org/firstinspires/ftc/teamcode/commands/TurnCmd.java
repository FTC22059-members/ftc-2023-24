package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

import java.util.function.DoubleSupplier;

public class TurnCmd extends CommandBase {

    private final DrivetrainSub drivetrainSub;
    private final double turnAngle;
    private final double turnSpeed;
    private Telemetry telemetry;
    private DoubleSupplier imuAngle;

    public TurnCmd(double ta, double ts, DrivetrainSub dts, Telemetry tm, DoubleSupplier imua) {
        turnAngle = ta;
        turnSpeed = ts;
        drivetrainSub = dts;
        telemetry = tm;
        imuAngle = imua;

        addRequirements(dts);
    }

    @Override
    public void initialize() {
        drivetrainSub.resetEncoders();
        drivetrainSub.move(0, turnSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSub.getDrive().stop();
    }


    @Override
    public boolean isFinished() {
        System.out.println("IMU Angle: " + imuAngle.getAsDouble());
        System.out.println("Is done? "+ (Math.abs(imuAngle.getAsDouble()) >= turnAngle));
        return Math.abs(imuAngle.getAsDouble()) >= turnAngle;
    }

}
