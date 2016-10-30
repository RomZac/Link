import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class SynchroStack {
	private Semaphore traffic = new Semaphore(1);
	private ConcurrentLinkedQueue<Integer> link;

	public SynchroStack() {
		link = new ConcurrentLinkedQueue<Integer>();
	}

	public void add(int n) {
		this.link.add(n);
	}

	public int pop() {
		return this.link.poll();
	}

	public boolean equals(Object obj) {
		try {
			traffic.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SynchroStack))
			return false;
		SynchroStack s = (SynchroStack) obj;
		boolean b = link.equals(s.link);
		traffic.release();
		return b;
	}

	public String toString() {
		try {
			traffic.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String s = link.toString();
		traffic.release();
		return s;
	}
}
