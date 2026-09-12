package org.firstinspires.ftc.teamcode.bot.den.black.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake implements BaseSubsystem {
    private final Telemetry telemetry;
    private DcMotor intakeMotor = null;
    private CRServo leftIntakeServo = null;
    private CRServo rightIntakeServo = null;

    public Intake(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    public void init(HardwareMap hardwareMap){
        intakeMotor = hardwareMap.get(DcMotor.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.intake);
        leftIntakeServo = hardwareMap.get(CRServo.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.leftIntakeServo);
        rightIntakeServo = hardwareMap.get(CRServo.class, org.firstinspires.ftc.teamcode.bot.den.black.Constants.Robot.ConfigNames.rightIntakeServo);
    }

    public void showTelemetry(){
    }

    public void setIntakePower(){
    }
}
