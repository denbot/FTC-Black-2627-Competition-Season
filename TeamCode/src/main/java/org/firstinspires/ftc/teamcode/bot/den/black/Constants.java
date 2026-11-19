package org.firstinspires.ftc.teamcode.bot.den.black;

public class Constants {
    public static class Game {
    }

    public static class Drive {
        private static final double wheelDiameterMM = 96;
        private static final double encoderTicksPerRev = 537.7;
        public static final double ticksPerMM = encoderTicksPerRev / (wheelDiameterMM * Math.PI);
        public static final double trackWidthMM = 402;
        public static final double driveSpeed = 1.0;
        public static final double turnSpeed = 1.0;
        public static final double holdTime = 0.25;
    }

    public static class Intake {
        public static final double rampUpPosition = 0.0;
        public static final double rampDownPosition = 0.75;
    }

    public static class Shooter {
        public static final double hiveSpeed = 1310;
        public static final double shootTime = 6.0;
    }

    public static class Robot {
        public static class ConfigNames {
            public static final String rightDrive = "right_drive";
            public static final String leftDrive = "left_drive";
            public static final String feederServo = "feeder";
            public static final String rightIntakeServo = "right_intake";
            public static final String leftIntakeServo = "left_intake";
            public static final String intake = "intake";
            public static final String shooter = "shooter";
        }
    }
}
