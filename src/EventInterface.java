import java.util.Queue;
/**
 * @author bshepard
 * Basic interfaces for the events. Acts as a facade.
 */
public interface EventInterface {
void addRacer(String bibNumber);
void trigger(int channelNumber);
Queue<Racer> moveAll();
void dnf();
void swap();
}
