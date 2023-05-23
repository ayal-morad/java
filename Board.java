package homeMork;
import java.util.Arrays;

public class Board {
	private Tile[][] tiles;
	
	public Board(Tile[][] arr) {
		this.tiles = new Tile[arr.length][arr[0].length];
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[0].length ; j++) {
				this.tiles[i][j] = arr[i][j];
			}
		}
	}
	
	public Board(String s) {
		boolean isCol = false;
		int row = 1 , col = 1;
		for(int i = 0 ; i < s.length()-1 ; i++) {
			if(s.charAt(i) == '|') {
				isCol = true;
				col++;
			}else {
				if((s.charAt(i) != ' ' && s.charAt(i+1) == ' ' && !isCol)) {
					row++;
				}
			}
		}
		this.tiles = new Tile[col][row];
		for(int i = 0 ; i < col ; i++) {
			for(int j = 0 ; j < row ; j++) {
				String st = "";
				int sum = 0;
				for (int k = 0 ; k < s.length() && (s.charAt(k) != ' ' && s.charAt(k) != '|') ; k++) {
					st += s.charAt(k);
				}
				if(st.equals("_")) {
					this.tiles[i][j] = null;
				}else {
					for(int m = 0 ; m < st.length() ; m++) {
						sum = (sum * 10) + (st.charAt(m) - '0');
					}
					this.tiles[i][j] = new Tile(sum);
				}
				boolean flg = false;
				st = "";
				for(int k = 0 ; k < s.length() ; k++) {
					if(!flg && (s.charAt(k) == ' '|| s.charAt(k) == '|')) {
						flg = true;
						k++;
					}
					if(flg) {
						st += s.charAt(k);
					}
				}
				s = st;
			}
		}
		printBord();
	}
	
	public Direction getTileDirection(int num) {
		int row = 0 , col = 0;
		boolean isThere = false;
		for(int i = 0 ; i < this.tiles.length ; i++) {
			for(int j = 0 ; j < this.tiles[0].length ; j++) {
				if(this.tiles[i][j] != null) {
					if(this.tiles[i][j].getValue() == num) {
						isThere = true;
						col = i;
						row = j;
					}
				}
			}
		}
		if(isThere) {
			if(row > 0 && this.tiles[col][row-1] == null) {
				return Direction.LEFT;
			}else if(row < this.tiles[0].length -1 && this.tiles[col][row + 1] == null) {
				return Direction.RIGHT;
			}else if(col > 0 && this.tiles[col - 1][row] == null) {
				return Direction.UP;
			}else if(col < this.tiles.length - 1 && this.tiles[col + 1][row] == null) {
				return Direction.DOWN;
			}else {return null;}
		}
		return null;
	}
	
	public void Move(String st , int num) {
		System.out.println("from move " + st + num);
		int row = 0 , col = 0;
		boolean isThere = false;
		for(int i = 0 ; i < this.tiles.length ; i++) {
			for(int j = 0 ; j < this.tiles[0].length ; j++) {
				if(this.tiles[i][j] != null && this.tiles[i][j].getValue() == num) {
					row = j;
					col = i;
					isThere = true;
				}
			}
		}
		if(isThere) {
			switch(st) {
			case "UP":
				if(this.tiles[col - 1][row] == null) {
					this.tiles[col - 1][row] = this.tiles[col][row];
					this.tiles[col][row] = null;
				}
				break;
			case "DOWN":
				if(this.tiles[col + 1][row] == null) {
					this.tiles[col + 1][row] = this.tiles[col][row];
					this.tiles[col][row] = null;
				}
				break;
			case "LEFT":
				if(this.tiles[col][row - 1] == null) {
					this.tiles[col][row - 1] = this.tiles[col][row];
					this.tiles[col][row] = null;
				}
				break;
			case "RIGHT":
				if(this.tiles[col][row + 1] == null) {
					this.tiles[col][row + 1] = this.tiles[col][row];
					this.tiles[col][row] = null;
				}
				break;
			}
		}
		this.printBord();
	}
	
	public void printBord() {
		System.out.println("---------------------------------");
		for(int i = 0 ; i < this.tiles.length ; i++) {
			for(int j = 0 ; j < this.tiles[0].length ; j++) {
				if(this.tiles[i][j] != null) {
					System.out.print(" " + this.tiles[i][j].getValue() + " ");
				}else {
					System.out.print(" _ ");
				}
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println();
		System.out.println();
	}

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }
    public Tile[][] getTiles(){
    	return this.tiles;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
    
}
