package once;

import java.util.ArrayList;

public class Point {
	public int id;
	public int neighbours = 0;
	public ArrayList<Point> targets = new ArrayList<Point>();
	public ArrayList<Point> sources = new ArrayList<Point>();
	
	public Point(int id) {
		this.id = id;
	}
	
	public Point randomGo() {
		if(targets.size() == 0) {
			return new Point(0);
		}
		int choose = (int)(Math.random() * targets.size());
		Point tarPoint = targets.get(choose);
		targets.remove(tarPoint);
		tarPoint.targets.remove(this);
		return tarPoint;
	}
	
	public void clear() {
		neighbours = 0;
		targets = new ArrayList<Point>();
		sources = new ArrayList<Point>();
	}
}
