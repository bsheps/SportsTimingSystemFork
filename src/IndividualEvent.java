import java.util.LinkedList;
import java.util.Queue;
/**
 * @author bshepard
 * IND event, used channels 1 (start), 2 (end)
 */
public class IndividualEvent implements EventInterface{
	Queue<Racer> WaitingToRace = (Queue<Racer>) new LinkedList<Racer>(),
			inTheRace = (Queue<Racer>) new LinkedList<Racer>(),
			finishers = (Queue<Racer>) new LinkedList<Racer>();
	boolean dnfracer = false;
	
	public void addRacer(String name){
		WaitingToRace.add(new Racer(name));
	}
	/**
	 * Indicates that a sensor was "triggered" and stores the corresponding time in 
	 * either the start or finish fields of the racer (odd channels start, even finish)
	 * @param chNum
	 * @param Time.getCurrentTime()
	 */
	public void trigger(int chNum) {
		if(chNum   == 1){	// odd means start time
			if(WaitingToRace.size() == 0) {
				Racer n = new Racer("noName");
				n.startRace(Time.getCurrentTime());
				inTheRace.add(n);
			}
			else {
				Racer x = WaitingToRace.remove();
				x.startRace(Time.getCurrentTime());
				inTheRace.add(x);
				x = null;
			}
		}
		else if(chNum == 2){	// even means end race
			if(inTheRace.size() == 0); //print.printThis("Channel " + chNum +" triggered at " + Time.time2formattedString(Time.getCurrentTime()) +"--ERROR: No more racers");
			else if(dnfracer) {
				finishers.add(inTheRace.remove()); // dnf doesn't get an end time
				dnfracer = false;
			}
			else {
				Racer y = inTheRace.remove();
				y.finishRace(Time.getCurrentTime());
				finishers.add(y);
			}
		}
	}
	
	public Queue<Racer> moveAll(){
		for (Racer x : inTheRace ) {
			x = inTheRace.remove();
			finishers.add(x);
		}
		for (Racer y : WaitingToRace) {
			y = WaitingToRace.remove();
			finishers.add(y);
		}
		
		return finishers;
	}
	
	public void dnf() {
		dnfracer = true;
		
	}
	/**
	 * swap the top 2 racer in inTheRace queue
	 */
	public void swap() {
		Racer tm = null;
		if(inTheRace.size() <2) {/*can't swap*/}
		else {
			Queue<Racer> holder = new LinkedList<Racer>();
			tm = inTheRace.remove();	// grab first racer in queue
			holder.add(inTheRace.remove()); // add second racer to top of new queue
			holder.add(tm);					// put first racer in second spot of new queue
			holder.addAll(inTheRace);		// move everyone else
			inTheRace = holder;				// reset to new queue
		}
	}
}