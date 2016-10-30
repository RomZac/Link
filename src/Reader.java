
public class Reader {
	public void push(SynchroStack link, int value) {
		link.add(value);
	}

	public int pop(SynchroStack link) {
		return link.pop();
	}
	
	public void push(SynchroStack link, int value, int thread) {
		link.add(value);
	}
}
