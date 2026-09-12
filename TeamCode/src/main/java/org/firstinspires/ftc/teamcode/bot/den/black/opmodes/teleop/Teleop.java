package org.firstinspires.ftc.teamcode.bot.den.black.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop", group = "Denbot")
public class Teleop extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Status", "Teleop Initialized");
    }

    @Override
    public void loop() {
    }
}