package org.firstinspires.ftc.teamcode.bot.den.black.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto2", group="Denbot", preselectTeleOp = "Teleop")
public class Auto2 extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Status", "Auto 2 Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void loop() {
    }
}