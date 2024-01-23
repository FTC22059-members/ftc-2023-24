package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

/**
 * This subsystem is dedicated to interfacing with the Intake
 * It can only turn on the intake
 */

public class ArmSub extends SubsystemBase {


    private Telemetry telemetry;

    private DcMotor arm;
    private CRServo wrist;
    /**
     * Constructor for the imu
     * @param hardwareMapImport The hardware map to be used in imu
     * @param telemetryImport The telemetry to be used for printing things
     */
    public ArmSub(HardwareMap hardwareMapImport, Telemetry telemetryImport) {
        this.telemetry = telemetryImport;

        this.arm = hardwareMapImport.get(DcMotor.class, "armMotor");
        this.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.wrist = hardwareMapImport.get(CRServo.class, "wristServo");
    }

    /**
     * Code to run the Intake's loop. Currently, prints heading.
     */
    @Override
    public void periodic() {
        //telemetry.addData("IMU heading", globalAngle);
    }

    /**
     * Reset the encoder
     */
    public void resetEncoder(){
        this.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    /**
     * Turns on the intake, with a speed.
     *
     * @param speed The speed that the intake goes.
     */
    public void setSpeed(double speed) {
        telemetry.addData("Arm called with speed of ", speed);
        arm.setPower(-speed*Constants.ArmConstants.armSpeedMultiplier);
    }

    public int getCurrentPosition(){
        return arm.getCurrentPosition();
    }

    public void trimWrist(double speed) {
        wrist.setPower(speed);
    }
}