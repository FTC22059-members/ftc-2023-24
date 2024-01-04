package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class WallDistanceLEDSub extends SubsystemBase {

    Telemetry telemetry;

    private DigitalChannel redLED;
    private DigitalChannel greenLED;


    public WallDistanceLEDSub(HardwareMap hardwareMap, Telemetry tm) {
        this.telemetry = tm;

        redLED = hardwareMap.get(DigitalChannel.class, "wallDistanceRed");
        greenLED = hardwareMap.get(DigitalChannel.class, "wallDistanceGreen");

        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);
    }

    public void setColor(String color) {
        if (color == "R") {
            redLED.setState(true);
            greenLED.setState(false);
        }else if (color == "Y") {
            redLED.setState(true);
            greenLED.setState(true);
        }else if (color == "G") {
            redLED.setState(false);
            greenLED.setState(true);
        }else{
            redLED.setState(false);
            greenLED.setState(false);
            telemetry.addData("invalid Color", color);
        }

    }
}
