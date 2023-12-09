package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LinearSlideSub extends SubsystemBase {

    Telemetry telemetry;

    public DcMotor linearSlideMotor;
    DigitalChannel limitSwitch;

    Boolean previouslyDown; // whether the arm was down last time move was ran.

    public LinearSlideSub(HardwareMap hardwareMap, Telemetry tm) {
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "linearSlideLimitSwitch");
        this.telemetry = tm;

        limitSwitch.setMode(DigitalChannel.Mode.INPUT);
//        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void periodic() {
        telemetry.addData("isArmDown", !limitSwitch.getState());
        telemetry.addData("armPos", linearSlideMotor.getCurrentPosition());
    }

    public DcMotor getMotor(){
        return linearSlideMotor;
    }

    public void move(double speed) {

        if (!(linearSlideMotor.getCurrentPosition() > 6000 && speed > 0)) {
            if (linearSlideMotor.getCurrentPosition()<=0) { // if the arm is all the way down
//                if (linearSlideMotor.getCurrentPosition() != 0) { // if the encoder is offset, reset it to 0
//                    linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // reset encoders to 0
//                    linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                }
                if (speed > 0) { // if the arm is moving up + its all the way down
                    linearSlideMotor.setPower(speed);
                } else { // if the arm is moving down + its all the way down
                    linearSlideMotor.setPower(0);
                }
            } else { // if the arm is not all the way down.
                linearSlideMotor.setPower(speed);
            }
        } else { // stop the slide from moving if we're above 6000
            linearSlideMotor.setPower(0);
    }

//         > 6000 + going up   ->    N
//         > 6000 + going down ->    Y
//         < 6000 + going up   ->    Y
//         < 6000 + going down ->    Y

    }
}