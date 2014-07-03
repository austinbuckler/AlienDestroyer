
public class Bullet extends GameObject {
	
	private int xSpeed = 0;
	private int ySpeed = 0;

	public Bullet(int x, int y) {
		super(x, y, 32, 32, "bullet");
	}

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

}
