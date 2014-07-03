
public class Player extends GameObject {
	
	private int health = -1;
	private int score = -1;
	
	private Bullet bullet;

	public Player(int x, int y) {
		super(x, y, 32, 32, "ship");
		this.health = 100;
		this.score = 0;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

}
