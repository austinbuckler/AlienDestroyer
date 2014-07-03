import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameKeyListener extends KeyAdapter {

	private final Assign8 main;
	private final Player player;
	
	public GameKeyListener(final Assign8 main) {
		this.main = main;
		this.player = main.getPlayer();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		if (main.isGameOver()) {
			return;
		}
		
		if (player.getX() < 0) {
			player.setX(0);
		}
		
		if (player.getX() > Assign8.APPLET_WIDTH - player.getWidth()) {
			player.setX(Assign8.APPLET_WIDTH - player.getWidth());
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setX(player.getX() - 10);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setX(player.getX() + 10);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // shoot
			if (player.getBullet() == null) {
				Bullet bullet = new Bullet(player.getX(), player.getY() - 30);
				bullet.setYSpeed(10);
				player.setBullet(bullet);
				main.getSoundFire().play();
			}
		}
	}

}
