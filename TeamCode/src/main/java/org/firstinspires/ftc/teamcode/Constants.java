package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagMetadata;

public final class Constants {
    public static final class DriveConstants {
        public static final double driveMaxSpeed = 0.8;
        public static final boolean doINeedMoreConstants = false;
        public static final double wheelDiameter = 96 / 25.4;
    }

    /**
     * This was our test april tag library for before the field gets revealed. Layout: <a href="https://docs.google.com/drawings/d/e/2PACX-1vSXED4Amh_mfP5qDGhN4UzgnkgQKdpLcihaxWyk1Cg05BWJo3tC6s2OdMPwIuXWW5RIZ7EfcgA3aXKB/pub?w=2396&h=2368"><img src="https://docs.google.com/drawings/d/e/2PACX-1vSXED4Amh_mfP5qDGhN4UzgnkgQKdpLcihaxWyk1Cg05BWJo3tC6s2OdMPwIuXWW5RIZ7EfcgA3aXKB/pub?w=2396&h=2368" width="300"></a>
     */
    public static final AprilTagLibrary OverclockedTagLibrary = new AprilTagLibrary.Builder()
            .addTag(new AprilTagMetadata(0, "Rat",    6, new VectorF(-72,-18), DistanceUnit.INCH, new Quaternion(0, 0, 0, 0, 0))) // 0º
            .addTag(new AprilTagMetadata(0, "Ox",     6, new VectorF(-72, 0),  DistanceUnit.INCH, new Quaternion(0, 0, 0, 0, 0))) // 0º
            .addTag(new AprilTagMetadata(0, "Tiger",  6, new VectorF(-72,18),  DistanceUnit.INCH, new Quaternion(0, 0, 0, 0, 0))) // 0º
            .addTag(new AprilTagMetadata(0, "Rabbit", 6, new VectorF(-18,72),  DistanceUnit.INCH, new Quaternion(-0.7071068f, 0, 0, 0.7071068f, 0)))  // 270º
            .addTag(new AprilTagMetadata(0, "Dragon", 6, new VectorF(0,72),    DistanceUnit.INCH, new Quaternion(-0.7071068f, 0, 0, 0.7071068f, 0)))  // 270º
            .addTag(new AprilTagMetadata(0, "Snake",  6, new VectorF(18,72),   DistanceUnit.INCH, new Quaternion(-0.7071068f, 0, 0, 0.7071068f, 0)))  // 270º
            .addTag(new AprilTagMetadata(0, "Horse",  6, new VectorF(72,18),   DistanceUnit.INCH, new Quaternion(0, 0, 0, 1, 0)))  // 180º
            .addTag(new AprilTagMetadata(0, "Goat",   6, new VectorF(72,0),    DistanceUnit.INCH, new Quaternion(0, 0, 0, 1, 0)))  // 180º
            .addTag(new AprilTagMetadata(0, "Monkey", 6, new VectorF(72,-18),  DistanceUnit.INCH, new Quaternion(0, 0, 0, 1, 0)))  // 180º
            .addTag(new AprilTagMetadata(0, "Rooster",6, new VectorF(18,-72),  DistanceUnit.INCH, new Quaternion(0.7071068f, 0, 0, 0.7071068f, 0)))  // 90º
            .addTag(new AprilTagMetadata(0, "Dog",    6, new VectorF(0,-72),   DistanceUnit.INCH, new Quaternion(0.7071068f, 0, 0, 0.7071068f, 0)))  // 90º
            .addTag(new AprilTagMetadata(0, "Pig",    6, new VectorF(-18,-72), DistanceUnit.INCH, new Quaternion(0.7071068f, 0, 0, 0.7071068f, 0)))  // 90º
            .build();
}
