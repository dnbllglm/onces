package once;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {
	public static int n = 5;
	public static ArrayList<Point> points = new ArrayList<Point>();
	public static void main(String[] args) throws IOException {
		for(int i = 1; i <= n; i++) 
		{
			Point point = new Point(i);
			points.add(point);
		}
		
		Path path = Paths.get("src/once/resources", "60");
		byte[] buffer = Files.readAllBytes(path);
		buffer[4] = 48;
		buffer[5] = 126;
		String content = new String(buffer);
		System.out.println(content);
		/*for(byte b : buffer) {
			System.out.println(b);
		}*/
		
		//outTest();
		//run(pstrs);
	}
	
	public static void init(String[] pstrs) {
		for(Point point : points) {
			point.targets.clear();
		}
		for(int i = 0; i < pstrs.length; i++){
			int start = Integer.parseInt(pstrs[i].split(",")[0]);
			int end = Integer.parseInt(pstrs[i].split(",")[1]);
			Point startPoint = points.get(start - 1);
			Point endPoint = points.get(end - 1);
			startPoint.targets.add(endPoint);
			endPoint.targets.add(startPoint);
		}
	}
	
	public static Point chooseStart() {
		int choose = (int)(Math.random() * points.size());
		Point point = points.get(choose);
		return point;
	}
	
	public static void run(String[] pstrs) {
		ArrayList<Integer> sequence;
		boolean success;
		do {
			init(pstrs);
			sequence = new ArrayList<Integer>();
			Point start = chooseStart();
			sequence.add(start.id);
			System.out.print(start.id);
			Point next = start;
			do {
				next = next.randomGo();
				if(next.id > 0) {
					System.out.print("->" + next.id);
					sequence.add(next.id);
				}
			} while(next.id != 0);
			success = true;
			for(Point point : points) {
				if(point.targets.size() > 0) {
					success = false;
					break;
				}
			}
			System.out.println();
		} while(!success);
		
		System.out.println("sequence:");
		for(int i : sequence) {
			System.out.print(i + ",");
		}
	}
	
	public static void outTest() {
		for(Point point : points) {
			System.out.print(point.id + ": ");
			for(Point tarPoint : point.targets) {
				System.out.print(tarPoint.id + ",");
			}
			System.out.println();
		}
	}
	
}
