package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants.DriveConstants;

public class DrivetrainSub extends SubsystemBase {

    Telemetry telemetry;

    Motor frontLeft;
    Motor frontRight;
    Motor backLeft;
    Motor backRight;

    MecanumDrive drive;

    public DrivetrainSub(HardwareMap hardwareMap, Telemetry tm) {
        this.frontLeft = new Motor(hardwareMap, "frontLeft");
        this.frontRight = new Motor(hardwareMap, "frontRight");
        this.backLeft = new Motor(hardwareMap, "backLeft");
        this.backRight = new Motor(hardwareMap, "backRight");

        this.drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);
        this.drive.setMaxSpeed(DriveConstants.driveMaxSpeed);
        this.telemetry = tm;
    }

    @Override
    public void periodic() {
        telemetry.addData("Drive", drive.toString());
    }
    /* This may be a quick fix, I don't know
        Returns Drive
     */
    public MecanumDrive getDrive(){
        return drive;
    }

    public void move(double forward, double rotation){
        this.move(forward, rotation, 0);
    }

    public void move(double forward, double rotation, double strafe){
        drive.driveRobotCentric(strafe, forward, rotation);
    }

    public void setMaxSpeed(double maxSpeed){
        this.drive.setMaxSpeed(maxSpeed);
    }

    public void resetEncoders(){
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();
    }

    public double getFrontLeftEncoderDistance() {
        telemetry.addData("Revs",frontLeft.encoder.getRevolutions());
        telemetry.addData("Final",frontLeft.encoder.getRevolutions() * DriveConstants.wheelDiameter * Math.PI);

        System.out.println("Revs: " + frontLeft.encoder.getRevolutions());
        System.out.println(("Final: "+(frontLeft.encoder.getRevolutions() * DriveConstants.wheelDiameter * Math.PI)));

        return frontLeft.encoder.getRevolutions() * DriveConstants.wheelDiameter * Math.PI;
    }
}