package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.subsystems.*;

import java.util.Locale;

public class LocalizationTest extends CommandOpMode {
    private LocalizationSub Localizer;
    @Override
    public void initialize() {
        Localizer = new LocalizationSub(hardwareMap, telemetry);
    }

    @Override
    public void run() {
        Pose2d robotPose = Localizer.getRobotPose();
        telemetry.addData("Robot Position: ", String.format(Locale.US, "(%6.2f, %6.2f)", robotPose.getX(), robotPose.getY()));
        telemetry.addData("Robot Rotation: ", robotPose.getRotation().getDegrees() + "º");

        telemetry.update();
    }
}
