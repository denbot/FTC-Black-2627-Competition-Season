package org.firstinspires.ftc.teamcode.bot.den.black.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Intake;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Shooter;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Drive;

@TeleOp(name = "Teleop", group = "Denbot")
public class Teleop extends OpMode {
    private final Intake intake = new Intake(telemetry);
    private final Shooter shooter = new Shooter(telemetry);
    private final Drive drive = new Drive(telemetry);
    @Override
    public void init() {
        telemetry.addData("Status", "Teleop Initialized");
    }

    @Override
    public void loop() {

        if(gamepad2.a){
            intake.spinIntakeForward();
            shooter.spinFeederForward();
            shooter.spinReverse();
        } else if(gamepad2.cross) {
            intake.spinIntakeBackwards();
        } else {
            intake.stopIntake();
        }

        if(gamepad2.circle){
            shooter.spinHive();
        } else if(gamepad2.triangle) {
            shooter.spinFlower();
        } else {
            shooter.stopSpinner();
        }

        if(gamepad2.right_trigger_pressed){
            shooter.spinFeederForward();
        } else if(gamepad2.cross) {
            shooter.spinFeederReverse();
        } else {
            shooter.stopFeeder();
        }

        drive.drive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        intake.showTelemetry();
        shooter.showTelemetry();
    }
}