package input;

public class Controller {

    public double x, y, z, rotation, xa, za, rotationa;
    public static boolean turnLeft = false;
    public static boolean turnRight = false;

    public void tick(boolean forward, boolean back, boolean left, boolean right, boolean jump, boolean crouch, boolean run) {

        double rotationSpeed = 0.005;
        double walkSpeed = 0.5;
        double jumpHeight = 0.5;
        double crouchHeight = 0.3;
        double xMove = 0;
        double zMove = 0;

        if(forward)
            zMove++;

        if(back)
            zMove--;

        if(left)
            xMove--;

        if(right)
            xMove++;

        if(turnLeft)
            rotationa -= rotationSpeed;

        if(turnRight)
            rotationa += rotationSpeed;

        if(jump) {
            y += jumpHeight;
            run = false;
        }

        if(crouch) {
            y -= crouchHeight;
            run = false;
        }

        if(run) {
            walkSpeed = walkSpeed*2;
        }

        xa += (xMove*Math.cos(rotation) + zMove*Math.sin(rotation))*walkSpeed;
        za += (zMove*Math.cos(rotation) - xMove*Math.sin(rotation))*walkSpeed;

        x += xa;
        y *= 0.9;
        z += za;
        xa *= 0.1;  // slowly stop
        za *= 0.1;
        rotation += rotationa;
        rotationa *= 0.8;
    }
}
