/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;




@TeleOp(name="Basic: Simple Move", group="Linear Opmode")
//@Disabled
public class SimpleMove extends LinearOpMode {

    // Declare OpMode members.


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor dc_motor_left = null;
    private DcMotor dc_motor_right = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        dc_motor_left  = hardwareMap.get(DcMotor.class, "dc_motor_left");
        dc_motor_right = hardwareMap.get(DcMotor.class, "dc_motor_right");

        dc_motor_left.setDirection(DcMotor.Direction.REVERSE);
        dc_motor_right.setDirection(DcMotor.Direction.FORWARD);

        double power=0;
        double turn=0;
        double leftpower=0;
        double rightpower=0;


        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            power=Math.min(Math.abs(-gamepad1.right_stick_y),power+(-gamepad1.right_stick_y*0.05));
            if (power<-0.5)
            {
                power=-0.5;
            }
            if (gamepad1.right_stick_y>-0.15 && gamepad1.right_stick_y<0.15) {
                power=0;
            }
            turn = gamepad1.right_stick_x*0.3;
            if (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) {
                turn=0;
            }
            leftpower=Range.clip(power+turn,-1,1);
            rightpower=Range.clip(power-turn,-1,1);

            dc_motor_left.setPower(leftpower);
            dc_motor_right.setPower(rightpower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Status", "Left Motor: " + leftpower);
            telemetry.addData("Status", "Right Motor: " + rightpower);
            telemetry.update();
        }
    }
}
