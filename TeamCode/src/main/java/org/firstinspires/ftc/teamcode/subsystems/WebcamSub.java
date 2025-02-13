package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/**
 * This subsystem is dedicated to interfacing with the Webcam
 * It can only return the webcamName (which is the webcam itself)
 */

public class WebcamSub extends SubsystemBase {


    private Telemetry telemetry;

    private WebcamName webcam;
    /**
     * Constructor for the webcam
     * @param hardwareMapParam The hardware map to be used in webcam
     * @param telemetryParam The telemetry to be used for printing things
     */
    public WebcamSub(HardwareMap hardwareMapParam, Telemetry telemetryParam) {
        this.telemetry = telemetryParam;

        this.webcam = hardwareMapParam.get(WebcamName.class, "Webcam 1");
    }

    public WebcamName getWebcamName(){
        return webcam;
    }

    /**
     * Code to run the webcam loop. Currently prints heading.
     */
    @Override
    public void periodic() {
    }
}