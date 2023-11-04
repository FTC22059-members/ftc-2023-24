package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants.DriveConstants;

/**
 * This subsystem is dedicated to interfacing with the drivetrain.
 * It's main purpose is to make the drivetrain move,
 * but it can also return distances and the drivetrain
 */

public class DrivetrainSub extends SubsystemBase {

    Telemetry telemetry;

    Motor frontLeft;
    Motor frontRight;
    Motor backLeft;
    Motor backRight;

    MecanumDrive drive;

    /***
     * Creates a new DriveTrain Sub constructor
     *
     * @param hardwareMap
     * @param tm
     */

    public DrivetrainSub(HardwareMap hardwareMap, Telemetry tm) {
        this.frontLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        this.frontRight = new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        this.backLeft = new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        this.backRight = new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);

        this.drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);
        this.drive.setMaxSpeed(DriveConstants.driveMaxSpeed);
        this.telemetry = tm;
    }

    @Override
    public void periodic() {}

    /**
     *
     * @return returns the mecanumDrive of the Drivetrain
     */
    public MecanumDrive getDrive(){
        return drive;
    }

    /**
     * Moves the robot with a robot centric viewpoint
     *
     * @param forward Forward value inputted to drive
     * @param rotation Rotational value inputted to drive
     * @param strafe Strafe value inputted to drive
     */
    public void move(double forward, double rotation, double strafe){
        drive.driveRobotCentric(-strafe, -forward, rotation);
    }

    public void move(double forward, double rotation){
        this.move(forward, rotation, 0);
    }

    /**
     * Sets the maxiumum speed for the drivetrain
     *
     * @param maxSpeed The maxium speed for the drivetrain
     */

    public void setMaxSpeed(double maxSpeed){
        this.drive.setMaxSpeed(maxSpeed);
    }

    /**
     * Resets the encoders.
     */

    public void resetEncoders(){
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();
    }

    /**
     * Gets the Front Left Encoder distance. Used for tracking distance driven
     *
     * @return The revolutions driven
     */

    public double getFrontLeftEncoderDistance() {
        telemetry.addData("Revs",frontLeft.encoder.getRevolutions());
        telemetry.addData("Final",frontLeft.encoder.getRevolutions() * DriveConstants.wheelDiameter * Math.PI);

        System.out.println("Revs: " + frontLeft.encoder.getRevolutions());
        System.out.println(("Final: "+(frontLeft.encoder.getRevolutions() * DriveConstants.wheelDiameter * Math.PI)));

        return frontLeft.encoder.getRevolutions() * DriveConstants.wheelDiameter * Math.PI;
    }
}