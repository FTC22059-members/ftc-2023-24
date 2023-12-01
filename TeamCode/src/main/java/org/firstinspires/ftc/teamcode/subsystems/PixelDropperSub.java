package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This subsystem is dedicated to interfacing with the depositor.
 * It can only drop the pixel.
 */

public class PixelDropperSub extends SubsystemBase {


    private Telemetry telemetry;

    private Motor pixelDropper;
    /**
     * Constructor for the imu
     * @param hardwareMapParam The hardware map to be used in imu
     * @param telemetryParam The telemetry to be used for printing things
     */
    public PixelDropperSub(HardwareMap hardwareMapParam, Telemetry telemetryParam) {
        this.telemetry = telemetryParam;

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
    public void dropPixel(){
        telemetry.addLine("Pixel dropper called. ");
    }

}