package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.Imu;

@Autonomous(name = "Auto Test 2023-24")
public class AutoTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Imu robotImu = new Imu(hardwareMap, telemetry);
        robotImu.init();

        //Initialize motor variables
        //Motor backLeft = hardwareMap.get(Motor.class, "backLeft");
        //Motor backRight = hardwareMap.get(Motor.class, "backRight");
        //Motor frontLeft = hardwareMap.get(Motor.class, "frontLeft");
        //Motor frontRight = hardwareMap.get(Motor.class, "frontRight");
        MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "frontLeft"),
                new Motor(hardwareMap, "frontRight"),
                new Motor(hardwareMap, "backLeft"),
                new Motor(hardwareMap, "backRight"));

        int forwardSpeed = 0;
        int turning = 0;

        final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        waitForStart();

        timer.reset();

        while (opModeIsActive()) {
            if (timer.milliseconds() <= 333){
                forwardSpeed = 1;
                turning = 0;
            } else if (timer.milliseconds() > 333 && timer.milliseconds() <= 666){
                forwardSpeed = 0;
                turning = 1;
            } else if (timer.milliseconds() > 666) {
                forwardSpeed = 0;
                turning = 0;
            }
            drive.driveFieldCentric(0, forwardSpeed, turning, robotImu.getAngle());
        }
    }
}
