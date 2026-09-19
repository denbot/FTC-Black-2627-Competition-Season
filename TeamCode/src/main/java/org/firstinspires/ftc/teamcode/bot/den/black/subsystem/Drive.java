package org.firstinspires.ftc.teamcode.bot.den.black.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive implements BaseSubsystem {
    private final Telemetry telemetry;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private double leftPower;
    private double rightPower;

    public Drive(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    public void init(HardwareMap hardwareMap){
        leftDrive = hardwareMap.get(DcMotor.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.leftDrive);
        rightDrive = hardwareMap.get(DcMotor.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.rightDrive);

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void showTelemetry(){
        telemetry.addData("Left Motor Power: ", leftDrive.getPower());
        telemetry.addData("Right Motor Power: ", rightDrive.getPower());
    }

    public void drive(double forward, double rotate) {
        leftDrive.setPower(forward+rotate);
        rightDrive.setPower(forward-rotate);
    }

    private boolean autoDriveDistance(double distance){
        return true;
    }

    public boolean autoRotate(double angle){
        return true;
    }

    public void resetEncoders(){
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
