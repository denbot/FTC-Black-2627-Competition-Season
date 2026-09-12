package org.firstinspires.ftc.teamcode.bot.den.black.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter implements BaseSubsystem {

    private final Telemetry telemetry;
    private DcMotorEx shooter = null;
    private CRServo feeder = null;

    public Shooter(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    public void init (HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotorEx.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.shooter);
        feeder = hardwareMap.get(CRServo.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.feederServo);
    }

    public void showTelemetry(){
    }

    public void setShooterVelocity(){
    }

    public void setFeederPower(){
    }

}
