package org.firstinspires.ftc.teamcode.bot.den.black.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter implements BaseSubsystem {

    private enum LaunchState {
        IDLE,
        SPINNING_UP,
        AT_SPEED,
    }
    private final Telemetry telemetry;
    private DcMotorEx shooter = null;
    private CRServo feeder = null;
    private double setpoint = 0.0;

    private LaunchState launchState = LaunchState.IDLE;

    public Shooter(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    public void init (HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotorEx.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.shooter);
        feeder = hardwareMap.get(CRServo.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.feederServo);
    }

    public void showTelemetry(){
        telemetry.addData("Shooter State: ", launchState);
        telemetry.addData("Shooter Speed: ", shooter.getVelocity());
        telemetry.addData("Shooter Setpoint: ", setpoint);
    }

    private void setShooterVelocity(double speed){
        shooter.setPower(speed);
    }

    public void spinHive(){
        setShooterVelocity(0.5);
    }

    public void spinFlower(){
        setShooterVelocity(0.3);
    }

    public void spinReverse(){
        setShooterVelocity(-0.3);
    }

    public void stopSpinner(){
        setShooterVelocity(0);
    }

    private void setFeederPower(double speed){
        feeder.setPower(speed);
    }

    public void spinFeederForward(){
        setFeederPower(1);
    }

    public void spinFeederReverse(){
        setFeederPower(-1);
    }

    public void stopFeeder(){
        setFeederPower(0);
    }

}
