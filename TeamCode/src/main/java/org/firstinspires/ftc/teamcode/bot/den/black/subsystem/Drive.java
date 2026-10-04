package org.firstinspires.ftc.teamcode.bot.den.black.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.bot.den.black.Constants;

public class Drive implements BaseSubsystem {
    private final Telemetry telemetry;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    public Drive(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    public void init(HardwareMap hardwareMap){
        leftDrive = hardwareMap.get(DcMotor.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.leftDrive);
        rightDrive = hardwareMap.get(DcMotor.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.rightDrive);

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void showTelemetry(){
        telemetry.addData("Left Motor Power: ", leftDrive.getPower());
        telemetry.addData("Right Motor Power: ", rightDrive.getPower());
        telemetry.addData("Left Motor Position: ", leftDrive.getCurrentPosition());
        telemetry.addData("Right Motor Position: ", rightDrive.getCurrentPosition());
    }

    public void drive(double forward, double rotate) {
        leftDrive.setPower(forward-rotate);
        rightDrive.setPower(forward+rotate);
    }

    public boolean autoDrive(double distance, double speed) {
        /* In this function we use a DistanceUnits. This is a class that allows us to accept different
         * input units depending on the user's preference (Inches, Millimeters, Feet, etc.).
         * The line below uses a "to" method to convert inches into millimeters.
         * Since goBilda provides the number of encoder ticks per millimeter of movement in our
         * constants file, we will convert to millimeters here.
         */
        double targetPosition = (DistanceUnit.INCH.toMm(distance) * Constants.Robot.ticksPerMM);

        leftDrive.setTargetPosition((int) targetPosition);
        rightDrive.setTargetPosition((int) targetPosition);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(speed);
        rightDrive.setPower(speed);

        /* Here we check if we are within tolerance of our target position or not. We calculate the
         * absolute error (distance from our setpoint regardless of if it is positive or negative)
         * and compare that to our tolerance. If the distance between where we are and where we want
         * to be is greater than our tolerance of 10 millimeters, then we return false.
         * Once we are within our tolerance, this function will return true and move on to the next step
         */
        return Math.abs(targetPosition - leftDrive.getCurrentPosition()) <
                (10 * Constants.Robot.ticksPerMM);
    }

    public boolean autoRotate(double angle, double speed){
        /* 360 degrees = 1 rotation = 2*pi Radians
         * Using radians helps simplify the math converting the circumference of our wheels to distance traveled.
         * To find the number of mm that our wheels need to travel, we just need to multiply the
         * requested angle in radians by the radius of our turning circle, or half the width of the robot.
         */
        double targetMm = AngleUnit.DEGREES.toRadians(angle)*(Constants.Robot.trackWidthMM/2);

        /*
         * We need to set the left motor to the inverse of the target so that we rotate instead
         * of driving straight.
         */
        double leftTargetPosition = -(targetMm*Constants.Robot.trackWidthMM);
        double rightTargetPosition = targetMm*Constants.Robot.trackWidthMM;

        leftDrive.setTargetPosition((int) leftTargetPosition);
        rightDrive.setTargetPosition((int) rightTargetPosition);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(speed);
        rightDrive.setPower(speed);

        //Same rule as linear driving, return true when we are within our tolerance, otherwise return false.
        return (Math.abs(leftTargetPosition - leftDrive.getCurrentPosition())) <
                (10 * Constants.Robot.trackWidthMM);
    }

    public void resetEncoders(){
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
