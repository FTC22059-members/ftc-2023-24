package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Constants;

public class IntakeSub extends SubsystemBase {


    private Telemetry telemetry;

    private Motor intake;
    /**
     * Constructor for the imu
     * @param hardwareMapImport The hardware map to be used in imu
     * @param telemetryImport The telemetry to be used for printing things
     */
    public IntakeSub(HardwareMap hardwareMapImport, Telemetry telemetryImport) {
        this.telemetry = telemetryImport;

        //this.intake = new Motor(hardwareMapImport, "intake", Motor.GoBILDA.RPM_312);
    }

    /**
     * Code to run the Intake's loop. Currently prints heading.
     */
    @Override
    public void periodic() {
        //telemetry.addData("IMU heading", globalAngle);
    }

    public void intake(double speed){
        telemetry.addData("Intake called with speed of ", speed);
    }
}