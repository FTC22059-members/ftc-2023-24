package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This subsystem is dedicated to interfacing with the Wrist
 * It can only turn on the wrist
 */

public class WristSub extends SubsystemBase {


    private Telemetry telemetry;

    private Servo wrist;
    /**
     * Constructor for the imu
     * @param hardwareMapImport The hardware map to be used in imu
     * @param telemetryImport The telemetry to be used for printing things
     */
    public WristSub(HardwareMap hardwareMapImport, Telemetry telemetryImport) {
        this.telemetry = telemetryImport;
        this.wrist = hardwareMapImport.get(Servo.class, "wristServo");
    }

    /**
     * Code to run the Wrist's loop. Currently prints heading.
     */
    @Override
    public void periodic() {
        //telemetry.addData("IMU heading", globalAngle);
    }

    /**
     * Turns on the intake, with a speed.
     *
     * @param position The position the wrist moves to.
     */
    public void setSpeed(double position) {
        telemetry.addData("Wrist called with position ", position);
        wrist.setPosition(position);
    }
}