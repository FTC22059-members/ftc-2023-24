package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class WallDistanceSub extends SubsystemBase {

    Telemetry telemetry;

    DistanceSensor distance1;
    DistanceSensor distance2;

    public WallDistanceSub(HardwareMap hardwareMap, Telemetry tm) {
        distance1 = hardwareMap.get(DistanceSensor.class, "Distance1");
        distance2 = hardwareMap.get(DistanceSensor.class, "Distance2");
        this.telemetry = tm;
    }

    public DistanceSensor getDistanceSensor(int sensorNumber) {
        if (sensorNumber == 1) {
            return distance1;
        } else {
            return distance2;
        }
    }

    public double getDistance(int sensorNumber) {
        if (sensorNumber == 1) {
            return distance1.getDistance(DistanceUnit.INCH);
        } else if (sensorNumber == 2) {
            return distance2.getDistance(DistanceUnit.INCH);
        } else if (sensorNumber == 3) {
            return (distance1.getDistance(DistanceUnit.INCH) + distance2.getDistance(DistanceUnit.INCH)) / 2;
        } else {
            System.out.println("Invalid Sensor Number, returning 0");
            return 0;
        }
    }

    public double getDistance() {
        return this.getDistance(3);
    }
}
