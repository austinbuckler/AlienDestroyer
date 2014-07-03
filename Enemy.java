
public class Enemy extends GameObject {

	private EnemyType type;
	private int animateFrame = 0;
	
	public Enemy(int x, int y, final EnemyType type) {
		super(x, y, 32, 32, type == EnemyType.TYPE_A ? "enemy1_1" : "enemy2_2");
		this.type = type;
	}
	
	public void animate() {
		if (type == EnemyType.TYPE_A) {
			if (animateFrame == 0) {
				setFileName("enemy1_2");
				animateFrame = 1;
			} else if (animateFrame == 1) {
				setFileName("enemy1_1");
				animateFrame = 0;
			}
		} else if (type == EnemyType.TYPE_B) {
			if (animateFrame == 0) {
				setFileName("enemy2_2");
				animateFrame = 1;
			} else if (animateFrame == 1) {
				setFileName("enemy2_1");
				animateFrame = 0;
			}
		}
	}
	
	public EnemyType getType() {
		return type;
	}

	public void setType(EnemyType type) {
		this.type = type;
	}

	public enum EnemyType {
		TYPE_A, TYPE_B;
	}

}
