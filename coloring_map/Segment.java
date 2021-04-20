package coloring_map;

public class Segment {

	private Point start;
	private Point end;
	
	public Segment(Point s, Point e) {
		this.start = s;
		this.end = e;
	}
	
	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}
}
