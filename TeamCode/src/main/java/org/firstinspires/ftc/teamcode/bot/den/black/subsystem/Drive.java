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
    }

    public void showTelemetry(){
    }

    public void arcadeDrive(double forward, double rotate) {
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
