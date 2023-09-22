package org.firstinspires.ftc.teamcode.subsystems;

import static java.lang.Math.*;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.Locale;

/**
 * This Subsystem returns it's guess at the robot position. This particular one uses a camera and AprilTags.
 */
public class AprilTagDetectionSub extends SubsystemBase {

    /**
     * {@link #aprilTag} is the variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * {@link #visionPortal} is the variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private String deviceName;
    private Pose2d robotOffset;
    /*
     +y is the forward direction of the robot.
     +x is the right of the robot when looking in the +y direction (from behind)
     */

    public boolean flagged;

    public AprilTagDetectionSub(HardwareMap hardwareMap, Telemetry telemetry, String deviceName, Pose2d robotOffset) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.deviceName = deviceName;
        this.robotOffset = robotOffset;

        // Create the AprilTag processor the easy way
        aprilTag = new AprilTagProcessor.Builder()
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setDrawTagID(true)            // Default: true, for all detections.
                .setDrawTagOutline(true)       // Default: true, when tag size was provided (thus eligible for pose estimation).
                .setDrawAxes(true)             // Default: false.
                .setDrawCubeProjection(true)   // Default: false.
                .build();

        // Create the vision portal the easy way.
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, deviceName), aprilTag);
    }

    @Override
    public void periodic() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format(Locale.US, "\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format(Locale.US, "XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format(Locale.US, "PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format(Locale.US, "RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                telemetry.addLine(String.format(Locale.US, "\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format(Locale.US, "Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }

        // Push telemetry to the Driver Station.
        telemetry.update();
    }

    public Pose2d getRobotPose() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        Translation2d averagePosition = new Translation2d(0, 0);
        Rotation2d averageRotation = new Rotation2d(0);

        for (AprilTagDetection detection : currentDetections) {
            VectorF tagAngleVector = detection.metadata.fieldOrientation.applyToVector(new VectorF(1, 0));
            double tagAngle = Math.atan2(tagAngleVector.get(1), tagAngleVector.get(0));
            double robotX = Math.cos(toRadians(detection.ftcPose.yaw) + toRadians(detection.ftcPose.bearing)) * detection.ftcPose.range;
            double robotY = Math.sin(toRadians(detection.ftcPose.yaw) + toRadians(detection.ftcPose.bearing)) * detection.ftcPose.range;

            averagePosition.plus(new Translation2d(robotX / currentDetections.size(), robotY / currentDetections.size()));

            averageRotation.rotateBy(new Rotation2d((toRadians(detection.ftcPose.yaw) + tagAngle - PI / 2) / currentDetections.size()));

        }

        return new Pose2d(averagePosition.plus(robotOffset.getTranslation()), averageRotation.rotateBy(robotOffset.getRotation()));
    }

    public void flag() {
        System.out.println("[AprilTagProcessor] " + deviceName + " has been flagged as inaccurate.");
        flagged = true;
    }
}
