package org.firstinspires.ftc.teamcode.bot.den.black;

public class Constants {
    public static class Game {
    }

    public static class Drive {
    }

    public static class Intake {
    }

    public static class Shooter {
    }

    public static class Robot {
        public static class ConfigNames {
            public static final String rightDrive = "right_drive";
            public static final String leftDrive = "left_drive";
            public static final String feederServo = "right_feeder";
            public static final String rightIntakeServo = "right_intake";
            public static final String leftIntakeServo = "left_intake";
            public static final String intake = "intake";
            public static final String shooter = "shooter";
        }

        private static final double wheelDiameterMM = 96;
        private static final double encoderTicksPerRev = 537.7;
        public static final double ticksPerMM = encoderTicksPerRev / (wheelDiameterMM * Math.PI);
        public static final double trackWidthMM = 404;
    }
}
