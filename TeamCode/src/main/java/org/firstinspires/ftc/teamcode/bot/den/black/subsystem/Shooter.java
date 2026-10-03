package org.firstinspires.ftc.teamcode.bot.den.black.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.bot.den.black.Constants;

public class Shooter implements BaseSubsystem {

    public enum ShooterState {
        NOT_RUNNING,
        SPINNING_UP,
        REVERSING,
        AT_SPEED,
    }
    private final Telemetry telemetry;
    private DcMotorEx shooter = null;
    private CRServo feeder = null;
    private double setpoint = 0.0;

    public ShooterState shooterState = ShooterState.NOT_RUNNING;

    public Shooter(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    public void init (HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotorEx.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.shooter);
        feeder = hardwareMap.get(CRServo.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.feederServo);


        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(
                        40,
                        0,
                        0,
                        12.5
                ));
    }

    public void showTelemetry(){
        telemetry.addData("Shooter State: ", shooterState);
        telemetry.addData("Shooter Speed: ", shooter.getVelocity());
        telemetry.addData("Shooter Setpoint: ", setpoint);
    }

    private void setShooterVelocity(double speed){
        shooter.setVelocity(speed);
        setpoint = speed;
    }

    public void spinShooterHive(){

        setShooterVelocity(Constants.Shooter.hiveSpeed);
        shooterState =
                shooter.getVelocity() < Constants.Shooter.hiveSpeed - 10 ?
                        ShooterState.SPINNING_UP :
                        ShooterState. AT_SPEED;
    }

    public void spinShooterFlower(){
        setShooterVelocity(Constants.Shooter.flowerSpeed);
        shooterState =
                shooter.getVelocity() < Constants.Shooter.flowerSpeed - 10 ?
                        ShooterState.SPINNING_UP :
                        ShooterState. AT_SPEED;
    }

    public void spinShooterReverse(){
        shooter.setPower(-0.5);
        shooterState = ShooterState.REVERSING;
    }

    public void stopShooter(){
        setShooterVelocity(0);
        shooterState = ShooterState.NOT_RUNNING;
    }

    private void setFeederPower(double speed){
        feeder.setPower(speed);
    }

    public void spinFeederForward(){
        setFeederPower(-0.4);
    }

    public void spinFeederReverse(){
        setFeederPower(0.4);
    }

    public void stopFeeder(){
        setFeederPower(0);
    }

}
