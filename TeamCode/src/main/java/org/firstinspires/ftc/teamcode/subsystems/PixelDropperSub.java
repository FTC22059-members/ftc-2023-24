package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This subsystem is dedicated to interfacing with the depositor.
 * It can only drop the pixel.
 */

public class PixelDropperSub extends SubsystemBase {


    private Telemetry telemetry;

    private CRServo pixelDropper;
    /**
     * Constructor for the imu
     * @param hardwareMapImport The hardware map to be used in imu
     * @param telemetryImport The telemetry to be used for printing things
     */
    public PixelDropperSub(HardwareMap hardwareMapImport, Telemetry telemetryImport) {
        this.telemetry = telemetryImport;
        this.pixelDropper = hardwareMapImport.get(CRServo.class, "pixelDropperServo");

        //this.pixelDropper = new Motor(hardwareMapParam, "Pixel Dropper", Motor.GoBILDA.RPM_312);
    }

    /**
     * Code to run the Intake's loop. Currently prints heading.
     */
    @Override
    public void periodic() {
        //telemetry.addData("IMU heading", globalAngle);
    }

    /**
     * The code to drop the pixel.
     */
    public void setSpeed(double speed){
        telemetry.addData("Output called with speed of ", speed);
        pixelDropper.setPower(speed);
    }
}