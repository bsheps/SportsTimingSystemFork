import java.util.Queue;

public interface EventInterface {
void addRacer(String bibNumber);
void trigger(int channelNumber);
Queue<Racer> moveAll();
void dnf();
void swap();
}