package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LocalizationSub extends SubsystemBase {
    private AprilTagDetectionSub camera1;

    public LocalizationSub(HardwareMap hardwareMap, Telemetry telemetry) {
        camera1 = new AprilTagDetectionSub(hardwareMap, telemetry, "Webcam 1", new Pose2d(new Translation2d(0, 6), new Rotation2d(0)));
    }

    public Pose2d getRobotPose() {
        if (!camera1.flagged) {
            return camera1.getRobotPose();
        } else {
            throw new RuntimeException("[Localization] There were no reliable sources of localization data.");
        }
    }
}
