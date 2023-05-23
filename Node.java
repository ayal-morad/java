package homeMork;

public class Node {
	private Node parent;
	private Action action;
	private State state;
	
	public static int howManySamle(int a , Tile[][]arr) {
		int count = 0 , col = arr[0].length , row = arr.length;
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				if(arr[i][j] != null) {
					if(arr[i][j].getValue() < a) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	public Node(State state , Action action , Node parent) {
		this.parent = parent;
		this.action = action;
		this.state = state;
	}

	public Node(State state) {
		this.state = state;
		this.parent = null;
		this.action = null;
	}
	
	public Node[] expand() {
		Action[] actions = this.state.actions();
		Node[] arr = new Node[actions.length];
		for(int i = 0 ; i < actions.length ; i++) {
			if(this.action != null && this.action.getTile() != actions[i].getTile()) {
				State pos = new State(this.state.getBoard());
				pos.result(actions[i]);
				arr[i] = new Node(pos , actions[i] , this);
			}else {
				if(this.action == null) {
					State pos = new State(this.state.getBoard());
					pos.result(actions[i]);
					arr[i] = new Node(pos , actions[i] , this);
				}
			}
		}
		System.out.print("From actions :");
		for(int i = 0 ; i < actions.length ; i++) {
			System.out.print(" " + actions[i] + " ");
		}
		System.out.println();
		Node[] arrToReturn;
		int count = 0;
		for(int i = 0 ; i < arr.length ; i++) {
			if(arr[i] != null) {
				count++;
			}
		}
		arrToReturn = new Node[count];
		count = 0;
		for(int i = 0 ; i < arr.length ; i++) {
			if(arr[i] != null) {
				arrToReturn[count] = arr[i];
				count++;
			}
		}
		return arrToReturn;
	}
	
	public int heuristicValue() {
		Tile[][] tiles = this.state.getBoard().getTiles();
		int count = 0 , col = tiles[0].length , row = tiles.length;
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				if(tiles[i][j] != null) {
					int size = howManySamle(tiles[i][j].getValue(), tiles);
					int nodeCol = size % col , nodeRow = size / col; // node col is where his col showd be and the same for node row
					count += Math.abs(i - nodeRow) + Math.abs(j - nodeCol);// Math.abs(i - nodeRow) how many steps we have to make in row to get there and the same for Math.abs(j - nodeCol)
				}else {
					if(i != row - 1 && j != col -1) {
						count += row - 1 - i + col - 1 + j;
					}
				}
			}
		}
		return count;
	}
	public State getState() {
		return this.state;
	}
	public Node getParent() {
		return this.parent;
	}
	public Action getAction() {
		return this.action;
	}
}
