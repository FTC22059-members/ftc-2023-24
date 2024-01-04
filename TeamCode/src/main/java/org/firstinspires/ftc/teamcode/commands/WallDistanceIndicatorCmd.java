package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.sensorEdgeDistance;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.WallDistanceLEDSub;
import org.firstinspires.ftc.teamcode.subsystems.WallDistanceSub;

/**
 * This command is dedicated to plane launching
 */

public class WallDistanceIndicatorCmd extends CommandBase {

    WallDistanceSub distanceSensor;
    WallDistanceLEDSub LED;
    private Telemetry telemetry;

    /**
     * Plane launch command.
     *
     * @param wallDistanceSubParam
     * @param wallDistanceLEDSubParam
     * @param telemetryParam
     */

    public WallDistanceIndicatorCmd(WallDistanceSub wallDistanceSubParam, WallDistanceLEDSub wallDistanceLEDSubParam, Telemetry telemetryParam) {
        distanceSensor = wallDistanceSubParam;
        LED=wallDistanceLEDSubParam;
        telemetry = telemetryParam;
        addRequirements(wallDistanceSubParam);
    }

    @Override
    public void execute() {
        double adjustedDistance = distanceSensor.getDistance()-sensorEdgeDistance;
        if (adjustedDistance>Constants.DriveConstants.sensorDangerDistance){
            LED.setColor("G");
            telemetry.addLine("color: green");
        }else if (adjustedDistance>Constants.DriveConstants.sensorDangerDistance){
            LED.setColor("Y");
            telemetry.addLine("color: yellow");
        }else{
            LED.setColor("R");
            telemetry.addLine("color: red");
        }
        telemetry.addData("Distance from wall", distanceSensor.getDistance());
        telemetry.addData("Adjusted Distance from wall", adjustedDistance);
    }
}