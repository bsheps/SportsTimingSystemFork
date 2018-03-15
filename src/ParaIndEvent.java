import java.util.LinkedList;
import java.util.Queue;

/** 
 * @author faassad
 * channels12 is start, channels24 is for finish 
 */

public class ParaIndEvent implements EventInterface{
	Queue<Racer> channels12= (Queue<Racer>) new LinkedList<Racer>(),
			finishers= (Queue<Racer>) new LinkedList<Racer>(),
			channels34 = (Queue<Racer>) new LinkedList<Racer>(),
			waitingToRace = (Queue<Racer>) new LinkedList<Racer>(); 
	boolean dnfracer=false;

	public void addRacer(String name) {
		waitingToRace.add(new Racer(name));
	}

	/**
	 * @param channelNumber
	 * @param Time.getCurrentTime()
	 */
	public void trigger(int channelNumber) {		
			if(channelNumber==1) {
				if(waitingToRace.size()==0) 
					channels12.add(new Racer("noName", Time.getCurrentTime()));
				else {
					Racer racer = waitingToRace.remove();
					racer.startRace(Time.getCurrentTime());
					channels12.add(racer);
				}
			}
			else if(channelNumber==2) {
				if(channels12.size() == 0) {/*nobody in queue*/}
				else if(dnfracer) {
					finishers.add(channels12.remove());
					dnfracer = false;
				}
				else {
					Racer racer = channels12.remove();
					racer.finishRace(Time.getCurrentTime());
					finishers.add(racer);
				}
			}
			else if(channelNumber==3) {
				if(waitingToRace.size()==0) 
					channels34.add(new Racer("noName", Time.getCurrentTime()));
				else {
					Racer racer = waitingToRace.remove();
					racer.startRace(Time.getCurrentTime());
					channels34.add(racer);
				}
			}
			else if(channelNumber==4) {
				if(channels34.size() == 0) {/*nobody in queue*/}
				else if(dnfracer) {
					finishers.add(channels34.remove());
					dnfracer = false;
				}
				else {
					Racer racer = channels34.remove();
					racer.finishRace(Time.getCurrentTime());
					finishers.add(racer);
				}
			}
	}

	public void endEvent(boolean endRace) {
		if(endRace) {
			while(channels12.size()!=0)
				finishers.add(channels12.remove());
			while(channels34.size()!=0)
				finishers.add(channels34.remove());
		}
	}
	
	public Queue<Racer> moveAll() {
		for (Racer x : channels12 ) {
			x = channels12.remove();
			finishers.add(x);
		}
		for (Racer y : channels34 ) {
			y = channels34.remove();
			finishers.add(y);
		}
		for (Racer z : waitingToRace) {
			z = waitingToRace.remove();
			finishers.add(z);
		}
		return finishers;
	}

	
	public void dnf() {
		// TODO Auto-generated method stub
		
	}

	
	public void swap() {
		// should never get here
	}

}
