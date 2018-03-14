
public class Channel extends ChronoTimer{
	private boolean[] channel = new boolean[13]; // 0 slot left empty
	private String[] sensor = new String[13]; // 0 slot left empty
	public Channel() {
		for(int i = 0; i < channel.length; i++) 
			channel[i] = false;
	}
	public boolean Toggle(int ch) {
		channel[ch] = !channel[ch];
		return channel[ch];
	}
	public boolean isChannelEnabled(int channelNumber) {
		return channel[channelNumber];
	}
	public boolean connectSensor(String sensortype, int chan) {
		if(sensor[chan] != null) {return false;/*can't connect because something is already there*/}
		else {
			sensor[chan] = sensortype;
			return true;
		}
	}
	public boolean disconnectSensor(int chan) {
		if(sensor[chan] == null) return false;
		else {
			sensor[chan]= null;
			return true;
		}
	}
}
