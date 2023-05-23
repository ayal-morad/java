package homeMork;
public class State {
	private Board board;
	
	public State(String st) {
		board = new Board(st);
	}
	public State(Board b) {
		this.board = new Board(b.getTiles());
	}
	
	public boolean isGoal() {
		int row = this.board.getTiles()[0].length , col = this.board.getTiles().length;
		int prev = 0;
		for(int i = 0 ; i < col ; i++) {
			for(int j = 0 ; j < row ; j++) {
				if(this.board.getTiles()[i][j] != null) {
					if(this.board.getTiles()[i][j].getValue() < prev) {
						return false;
					}else {
						prev = this.board.getTiles()[i][j].getValue();
					}
				}
			}
		}
		if(this.board.getTiles()[this.board.getTiles().length-1][this.board.getTiles()[0].length -1] == null) {
			return true;
		}
		return false;
	}
	
	public Action[] actions() {
		Action[] arr = new Action[4];
		int row = this.board.getTiles()[0].length , col = this.board.getTiles().length;
		int index = 0;
		for (int i = 0 ; i < col ; i++) {
			for(int j = 0 ; j < row ; j++) {
				if(this.board.getTiles()[i][j] != null) {
					Direction d = this.board.getTileDirection(this.board.getTiles()[i][j].getValue());
					if(d != null) {
						arr[index] = new Action(this.board.getTiles()[i][j].getValue() , d);
						index++;
					}
				}
			}
		}
		Action[] arrToReturn;
		int count = 0;
		for(int i = 0 ; i < 4 ; i++) {
			if(arr[i] != null) {
				count++;
			}
		}
		Direction[] di = new Direction[4];
		di[0] = Direction.UP;
		di[1] = Direction.DOWN;
		di[2] = Direction.RIGHT;
		di[3] = Direction.LEFT;
		arrToReturn = new Action[count];
		int index2 = 0;
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				if(arr[j] != null) {
					if(arr[j].getDirection() == di[i]) {
						arrToReturn[index2] = arr[j];
						index2++;
 					}
				}
			}
		}
		return arrToReturn;
	}
	
	public Board result(Action act) {
		int tileNum = 0;
		String direction = "";
		String st = act.toString();
		int ind1 = 0 , ind2 = 0;
		for(int i = 0 ; i < st.length() ; i++) {
			if(st.charAt(i) == ' ') {
				if(ind1 == 0) { // because we are sure that is action string there is tow spaces
					ind1 = i;
				}else {
					ind2 = i;
				}
			}
		}
		String num = st.substring(ind1 + 1, ind2); // get the tile number
		for(int i = 0 ; i < num.length() ; i++) {
			tileNum = (tileNum * 10) + (num.charAt(i) - '0'); //if was number > 9
		}
		
		direction = st.substring(ind2 + 1);
		this.board.Move(direction, tileNum);
		
		return new Board(this.board.getTiles());
	}
	
	public Board getBoard() {
		return this.board;
	}

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
