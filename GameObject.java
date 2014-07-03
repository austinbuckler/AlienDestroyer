import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class GameObject {

	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private boolean visible;
	private String fileName;
	
	private final Rectangle hitbox;
	
	public GameObject(int x, int y, int width, int height, String fileName) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fileName = fileName;
		this.hitbox = new Rectangle();
		this.visible = true;
	}
	
	public void draw(Graphics g, Assign8 main) {
		if (!visible) {
			return;
		}
		
		Image img = main.getImage(main.getDocumentBase(), "img/" + fileName + ".png");
		
		g.drawImage(img, x, y, main);
	}
	
	public boolean collidesWithSprite(GameObject sprite) {
		getHitbox().setBounds(x, y, 30, 30);
		sprite.getHitbox().setBounds(sprite.getX(), sprite.getY(), 30, 30);
		return getHitbox().intersects(sprite.getHitbox()) && sprite.isVisible();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

}
