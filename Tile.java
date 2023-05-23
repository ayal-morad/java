package homeMork;
public class Tile {
	private int value;
	
	public Tile(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return this.value == tile.getValue();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}