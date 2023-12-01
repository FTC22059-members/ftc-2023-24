package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.library.Color;

public final class Constants {
    public static final class DriveConstants{
        public static final double driveMaxSpeed = 0.7;
        public static final boolean doINeedMoreConstants = true;
        public static final double wheelDiameter = 96/25.4;
    }

    public static final class Colors {
        public static final Color RED = new Color(235, 54, 26);
        public static final Color ORANGE = new Color(235, 101, 26);
        public static final Color YELLOW = new Color(230, 208, 13);
        public static final Color GREEN = new Color(41, 201, 32);
        public static final Color BLUE = new Color(32, 125, 201);
        public static final Color PURPLE = new Color(137, 32, 201);
        public static final Color WHITE = new Color(255, 255, 255);
        public static final Color BLACK = new Color(0, 0, 0);
        public static final Color OVERCLOCKED = new Color(235, 19, 139);
    }
    public static final class IntakeConstants{
        public static final double defaultIntakeSpeed = 1.0;
    }

    public static final class ArmConstants{
        public static final double defaultArmSpeed = 0.5;
    }

    public static final class DroneConstants{
        public static final double launchPosition = 0.3;
        public static final double resetPosition = 0;
    }
}
