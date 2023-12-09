package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

/**
 * This subsystem is dedicated to interfacing with the Intake
 * It can only turn on the intake
 */

public class ArmSub extends SubsystemBase {


    private Telemetry telemetry;

    public DcMotor arm;
    /**
     * Constructor for the imu
     * @param hardwareMapImport The hardware map to be used in imu
     * @param telemetryImport The telemetry to be used for printing things
     */
    public ArmSub(HardwareMap hardwareMapImport, Telemetry telemetryImport) {
        this.telemetry = telemetryImport;
        this.arm = hardwareMapImport.get(DcMotor.class, "armMotor");

        this.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Code to run the Intake's loop. Currently prints heading.
     */
    @Override
    public void periodic() {
        //telemetry.addData("IMU heading", globalAngle);
    }

    /**
     * Turns on the intake, with a speed.
     *
     * @param speed The speed that the intake goes.
     */
    public void setSpeed(double speed) {
        // Ground Limit: 1700
        // Outtake Limit: -50
        // > 1700 going up -> YES
        // > 1700 going down -> NO
        // < -50 going up -> NO
        // < -50 going down ->YES
        if ((arm.getCurrentPosition() > 1700 && speed > 0) || (arm.getCurrentPosition() < -50 && speed < 0)) {
            telemetry.addData("Arm Speed", speed);
            arm.setPower(-speed*Constants.ArmConstants.armSpeedMultiplier);
        } else {
            arm.setPower(0);
            telemetry.addData("Arm Speed", speed);
        }
    }

    public int getCurrentPosition(){
        return arm.getCurrentPosition();
    }
}