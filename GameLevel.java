import java.awt.Graphics;


public class GameLevel {

	private final Assign8 main;
	private final Graphics g;
	
	public GameLevel(final Assign8 main, final Graphics g) {
		this.main = main;
		this.g = g;
	}
	
	public void populateList() {
		drawInitAliens(true);
	}
	
	private void drawInitAliens(boolean update) {
		drawAlien(128, 0, Enemy.EnemyType.TYPE_A, update);
		drawAlien(288, 0, Enemy.EnemyType.TYPE_B, update);
		drawAlien(448, 0, Enemy.EnemyType.TYPE_A, update);
		drawAlien(640, 0, Enemy.EnemyType.TYPE_B, update);
		
		drawAlien(32, 32, Enemy.EnemyType.TYPE_A, update);
		drawAlien(192, 32, Enemy.EnemyType.TYPE_B, update);
		drawAlien(352, 32, Enemy.EnemyType.TYPE_A, update);
		drawAlien(512, 32, Enemy.EnemyType.TYPE_B, update);
		drawAlien(704, 32, Enemy.EnemyType.TYPE_A, update);
		
		drawAlien(96, 64, Enemy.EnemyType.TYPE_A, update);
		drawAlien(256, 64, Enemy.EnemyType.TYPE_A, update);
		drawAlien(416, 64, Enemy.EnemyType.TYPE_A, update);
		drawAlien(608, 64, Enemy.EnemyType.TYPE_A, update);
	}
	
	public void drawAlien(int x, int y, Enemy.EnemyType type, boolean update) {
		Enemy enemy = new Enemy(x, y, type);
		enemy.draw(g, main);
		if (update) {
			Assign8.getEntities().add(enemy);
		}
	}

}
