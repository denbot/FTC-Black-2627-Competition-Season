package org.firstinspires.ftc.teamcode.bot.den.black.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bot.den.black.AutoStates;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Drive;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Intake;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Shooter;

@Autonomous(name = "Auto1", group="Denbot", preselectTeleOp = "Teleop")
public class ExampleAuto extends OpMode {
    //The ordered sequence of actions - This is what the robot will try to do
    private final AutoStates[] autoRoutine = {
            AutoStates.WAIT,
            AutoStates.RESET,
            AutoStates.SHOOT,
            AutoStates.DRIVE,
            AutoStates.ROTATE,
            AutoStates.END
    };
    //This variable will step through the array above so we complete the steps in order
    private int step = 0;
    //Adjustable wait time in case our teammate needs us to delay the start of our auto
    private double waitTime = 0.0;
    private final ElapsedTime waitTimer = new ElapsedTime();
    private final Intake intake = new Intake(telemetry);
    private final Shooter shooter = new Shooter(telemetry);
    private final Drive drive = new Drive(telemetry);

    @Override
    public void init() {
        telemetry.addData("Status", "Auto 1 Initialized");
        intake.init(hardwareMap);
        shooter.init(hardwareMap);
        drive.init(hardwareMap);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Wait Time", waitTime);
        waitTimer.reset();

        /*Press up on the d-pad to increase the wait time,
         *press down to decrease as long as the wait time is not negative.
        */
        if(gamepad1.dpadUpWasReleased()){
            waitTime += 0.5;
        } else if(gamepad1.dpadDownWasReleased() && waitTime>0.0){
            waitTime -= 0.5;
        }
    }

    @Override
    public void loop() {
        telemetry.addData("State", autoRoutine[step]);
        /*Use a switch statement to perform the same function as a nested if statement without the complicated logic.
         * In this case, the statement checks the value of the auto routine array at the step indicated
         * by the counter and runs the applicable instructions based on the state
         * (RESET, WAIT, SHOOT, DRIVE, or END)
         */
        switch(autoRoutine[step]){
            case RESET:
                //Reset the wait timer and drive encoders, then move on
                drive.resetEncoders();
                waitTimer.reset();
                step++;
                break;
            case WAIT:
                //Wait the specified amount of time in our waitTime variable, then move on
                if(waitTimer.seconds() > waitTime){
                    step++;
                }
                break;
            case SHOOT:
                //Perform the shooting sequence for the specified amount of time, then move on
                shooter.spinShooterHive();
                if(shooter.shooterState == Shooter.ShooterState.AT_SPEED){
                    intake.spinIntakeForward();
                    shooter.spinFeederForward();
                } else{
                    intake.stopIntake();
                    shooter.stopFeeder();
                }
                if(waitTimer.seconds() > 10) {
                    step++;
                }
                break;
            case DRIVE:
                //Drive forward 12 inches, then move on
                if(drive.autoDrive(12,0.5)){
                    step++;
                }
                break;
            case ROTATE:
                //Rotate 180 degrees, then move on
                if(drive.autoRotate(180,0.2)){
                    step++;
                }
                break;
            case END:
                //Stay in this state until the end of auto, stop all motors
                intake.stopIntake();
                shooter.stopShooter();
                shooter.stopFeeder();
                drive.drive(0,0);
                break;
        }
    }
}