package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSub extends SubsystemBase {

    Telemetry telemetry;

    public DcMotor intakeMotor;

    Boolean previouslyDown; // whether the arm was down last time move was ran.

    public IntakeSub(HardwareMap hardwareMap, Telemetry tm) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        this.telemetry = tm;

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void periodic() {
    }

    public DcMotor getMotor() {
        return intakeMotor;
    }

    public void move(double speed) {
        intakeMotor.setPower(speed);
    }
}