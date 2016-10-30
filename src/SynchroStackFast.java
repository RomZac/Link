import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class SynchroStackFast {
	private Semaphore trafficMod = new Semaphore(1);
	private Semaphore trafficRead = new Semaphore(1);
	private ConcurrentLinkedQueue<Integer> link;

	public SynchroStackFast() {
		link = new ConcurrentLinkedQueue<>();
	}

	public void push(int n) {
		try {
			do {
				if (trafficMod.availablePermits() == 1 && trafficRead.availablePermits() == 1) {
					trafficMod.acquire();
					break;
				} else
					trafficMod.wait();
			} while (true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		link.add(n);
		trafficMod.release();
	}

	public int pop() {
		try {
			do {
				if (trafficMod.availablePermits() == 1 && trafficRead.availablePermits() == 1) {
					trafficMod.acquire();
					break;
				} else
					trafficMod.wait();
			} while (true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int t = link.poll();
		trafficMod.release();
		return t;
	}

	public boolean equals(Object obj) {
		try {
			do {
				if (trafficMod.availablePermits() == 1)
					trafficRead.acquire();
				else if (trafficMod.availablePermits() == 0)
					trafficRead.wait();
			} while (true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SynchroStackFast))
			return false;
		SynchroStackFast stack = (SynchroStackFast) obj;
		boolean l = link.equals(stack.link);
		trafficRead.release();
		return l;
	}

	public String toString() {
		try {
			do {
				if (trafficMod.availablePermits() == 1)
					trafficRead.acquire();
				else if (trafficMod.availablePermits() == 0)
					trafficRead.wait();
			} while (true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return link.toString();
	}
}
