import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assign8 extends Applet {

	private Player player = null;
	private GameKeyListener keyListener = null;
	private GameLevel level = null;
	private GameAlarm alarm = null;
	private AudioClip soundWin = null;
	private AudioClip soundLose = null;
	private AudioClip soundFire = null;
	private AudioClip soundHit = null;
	private Font font = null;

	/** Flashing Fix **/
	private Image image = null;
	private Graphics gfx = null;

	private static final List<GameObject> entities = new ArrayList<GameObject>();

	/** Constants **/
	public static final int APPLET_WIDTH = 800;
	public static final int APPLET_HEIGHT = 600;
	public static final int GOAL = 500;

	private boolean gameOver = false;
	private boolean startup = false;
	private boolean debug = false;
	private String message = null;

	@Override
	public void init() {
		super.init();
		
		alarm = new GameAlarm(46);
		player = new Player(400, APPLET_HEIGHT - 32);
		keyListener = new GameKeyListener(this);

		addKeyListener(keyListener);
		setBackground(Color.BLACK);
		
		this.soundFire = this.getAudioClip(getDocumentBase(), "snd/soundfire.wav");
		this.soundWin = this.getAudioClip(getDocumentBase(), "snd/soundwin.wav");
		this.soundHit = this.getAudioClip(getDocumentBase(), "snd/soundhit.wav");
		this.soundLose = this.getAudioClip(getDocumentBase(), "snd/soundlose.wav");
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")).deriveFont(21f);
			setFont(font);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		requestFocus();

		startup = true;

		alarm.createAlarm();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (startup) {
			getEntities().clear();
			level = new GameLevel(this, g);
			level.populateList();
			if (!this.hasFocus()) {
				this.requestFocus();
			}
			startup = false;
		}

		int timeLeft = alarm.getTimeLeft();
		
		if (timeLeft > 40) {
			g.setColor(Color.WHITE);
			g.drawString("Destroy the aliens till you have", 100, 300);
			g.drawString("reached " + GOAL + " points", 100, 325);
		}
		
		if (debug) {
			g.setColor(Color.GREEN);
			for (int i = 0; i < APPLET_WIDTH; i++) {
				g.drawLine(0, i * 32, APPLET_WIDTH, i * 32);
				g.drawString("" + i * 32, 32 * i, 32);
			}
			
			for (int i = 0; i < APPLET_HEIGHT; i++) {
				g.drawLine(i * 32, 0, i * 32, APPLET_HEIGHT);
				g.drawString("" + i * 32, 0, 32 * i + 32);
			}
		}
		
		for (int i = 0; i < getEntities().size(); i++) {
			GameObject entity = entities.get(i);
			
			if (entity == null) {
				continue;
			}
			
			if (entity instanceof Enemy) {
				final Enemy enemy = (Enemy) getEntities().get(i);
				enemy.draw(g, this);
				if (!gameOver) {
					enemy.setY(enemy.getY() + 1);
				}
				
				if (enemy.getY() >= APPLET_HEIGHT - 32) {
					player.setHealth(0);
				}
			}
			
			if (player.getBullet() != null) {
				if (player.getBullet().collidesWithSprite(entity)) {
					if (entity instanceof Enemy) {
						soundHit.play();
						player.setScore(player.getScore() + 50);
						entity.setVisible(false);
						player.getBullet().setVisible(false);
						player.setBullet(null);
					}
				}
			}
			
		}
		
		if (player.getBullet() != null) {
			player.getBullet().draw(g, this);
			player.getBullet().setY(player.getBullet().getY() - player.getBullet().getYSpeed());
			if (player.getBullet().getY() < 0) {
				player.setBullet(null);
			}
		}
		
		player.draw(g, this);
		
		repaint();
		delay(30);
		
		this.message = "Time: " + alarm.getTimeLeft() + " Health: "
				+ player.getHealth() + " Score: " + player.getScore();
		
		if (timeLeft <= 0 || player.getHealth() <= 0) {
			message = "You lose";
			soundLose.play();
			setBackground(new Color(255, 66, 57));
			g.setColor(Color.WHITE);
			g.drawString("YOU LOSE", 200, 300);
			showStatus(message);
			gameOver = true;
			return;
		} else {
			if (player.getScore() == GOAL) {
				message = "You win";
				g.setColor(Color.WHITE);
				g.drawString("YOU WIN", 200, 300);
				soundWin.play();
				setBackground(new Color(9, 178, 52));
				showStatus(message);
				gameOver = true;
				return;
			}
		}
		
		this.showStatus(message);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(getWidth(), getHeight());
			gfx = image.getGraphics();
		}

		gfx.setColor(getBackground());
		gfx.fillRect(0, 0, getWidth(), getHeight());

		gfx.setColor(getForeground());
		paint(gfx);

		g.drawImage(image, 0, 0, this);
	}

	public static int random(int maxRange) {
		return (int) Math.round((Math.random() * maxRange));
	}

	public void delay(long timeInMillis) {
		long currenTime = System.currentTimeMillis();
		long totalTime = currenTime + timeInMillis;
		while (System.currentTimeMillis() < totalTime);
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public static List<GameObject> getEntities() {
		return entities;
	}

	public AudioClip getSoundWin() {
		return soundWin;
	}

	public AudioClip getSoundLose() {
		return soundLose;
	}

	public AudioClip getSoundFire() {
		return soundFire;
	}

	public AudioClip getSoundHit() {
		return soundHit;
	}

	private static final long serialVersionUID = 1L;

}
