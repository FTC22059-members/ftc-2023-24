package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants.DriveConstants;

public class LinearSlideSub extends SubsystemBase {

    Telemetry telemetry;

    DcMotor linearSlideMotor;

    public LinearSlideSub(HardwareMap hardwareMap, Telemetry tm) {
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");
        this.telemetry = tm;
    }

    @Override
    public void periodic() {}

    public DcMotor getMotor(){
        return linearSlideMotor;
    }

    public void move(int direction, double speed){
        /*
            direction: [1,-1] -- The direction, 1 for up, -1 for down.
            speed: 0 - 1 -- The speed. Setting it to zero makes the motor stop.
        */
        linearSlideMotor.setPower(direction*speed);
    }



//    public void resetEncoders(){
//        linearSlideMotor.resetEncoder();
//    }
}