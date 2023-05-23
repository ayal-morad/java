package homeMork;

public class Action {
	private int tile;
	private Direction direction;
	
	public Action(int num , Direction d) {
		this.tile = num;
		this.direction = d;
	}
	
	public String toString() {
		String st = "Move " + this.tile;
		switch (this.direction){
		case UP : 
			st += " UP";
			break;
		case DOWN:
			st += " DOWN";
			break;
		case LEFT:
			st += " LEFT";
			break;
		case RIGHT:
			st += " RIGHT";
			break;
		}
		return st;
	}
	public Direction getDirection(){
		return this.direction;
	}
	public int getTile() {
		return this.tile;
	}
	
}
