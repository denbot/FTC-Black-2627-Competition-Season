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
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad2.a){
            // intakes balls to prepare to shoot
            intake.spinIntakeForward();
        } else if(gamepad2.cross) {
            // unloads stored balls in case of a jam
            intake.spinIntakeBackwards();
        } else {
            intake.stopIntake();
        }

        if(gamepad2.circle){
            // prepare to shoot balls at hive
            shooter.spinShooterHive();
        } else if(gamepad2.triangle) {
            // prepare to shoot balls at flower
            shooter.spinShooterFlower();
        } else if(gamepad2.a){
            shooter.spinShooterReverse();
        } else {
            shooter.stopShooter();
        }

        if(gamepad2.right_trigger_pressed){
            // shoot balls at target
            if(shooter.shooterState == Shooter.ShooterState.AT_SPEED){
                shooter.spinFeederForward();
                intake.spinIntakeForward();
            }
        } else {
            shooter.stopFeeder();
        }

        drive.drive(gamepad1.left_stick_y * 0.5, gamepad1.right_stick_x * 0.5);

        intake.showTelemetry();
        shooter.showTelemetry();
    }
}