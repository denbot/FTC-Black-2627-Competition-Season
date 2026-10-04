package org.firstinspires.ftc.teamcode.bot.den.black.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bot.den.black.AutoStates;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Drive;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Intake;
import org.firstinspires.ftc.teamcode.bot.den.black.subsystem.Shooter;

@Autonomous(name = "Auto2", group="Denbot", preselectTeleOp = "Teleop")
public class Auto2 extends OpMode {
    //The ordered sequence of actions - This is what the robot will try to do
    private final AutoStates[] autoRoutine = {
    };
    //Adjustable wait time in case our teammate needs us to delay the start of our auto
    private double waitTime = 0.0;
    private ElapsedTime waitTimer = new ElapsedTime();
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
        //This variable will step through the array above so we complete the steps in order
        int step = 0;
        telemetry.addData("State", autoRoutine[step]);
        /*Use a switch statement to perform the same function as a nested if statement without the complicated logic.
         * In this case, the statement checks the value of the auto routine array at the step indicated
         * by the counter and runs the applicable instructions based on the state
         */
        switch(autoRoutine[step]){
        }
    }
}