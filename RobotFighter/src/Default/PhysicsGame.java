package Default;

import org.newdawn.slick.SlickException;

public class PhysicsGame 
{
	private Player player;
	//private PlayerController playerController;
	private Map map;
	private boolean jumping = false;

	private float g = 0;
	private int elapsedTimeGravity = 0;
	private int elapsedTimeJump = 0;
	private int i = 50;

	//Constructeur
	public PhysicsGame(Player player, Map map)
	{
		this.map = map;
		this.player = player;
	}

	/////////////////////////
	///Méthodes de gravité///
	/////////////////////////
	
	public void update(int delta) throws SlickException
	{
		this.gravity(delta);
		this.jump(delta);
	}
	
	public void gravity(int delta) throws SlickException
	{
		float posY = this.player.getY();
		float posX = this.player.getX();
		boolean collision = this.map.isCollision(posX, posY);
		elapsedTimeGravity += delta;
  	   	if(elapsedTimeGravity >= 2)
  	   	{
  	   	elapsedTimeGravity = 0;
			if(!collision || this.jumping == false)
			{
				posY = posY + 1 * g;
				g = (float) (g + 0.1);
				this.player.setY(posY);
			}
			if(collision)
			{
				g = 0;
			}
  	  	}
	}
	
	//////////////////////
	///Méthodes de saut///
	//////////////////////
	
	public void jump(int delta) throws SlickException
	{
		float posY = this.player.getY();
		float posX = this.player.getX();
		boolean collision = this.map.isCollision(posX, posY);
		if (jumping)
		{
			/*if(collision)
			{*/
				elapsedTimeJump += delta;
				if(elapsedTimeJump >= 30)
				{
					if(i>0)
					{
						elapsedTimeJump = 0;
						posY = posY - 5;// * g;
						//g = (float) (g - 0.02);
						this.player.setY(posY);
						i--;
					}
					if(i == 0)
					{
						this.jumping = false;
					}
					
				}
			//}
		}
	}
	
	//Setters Getters
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

}
