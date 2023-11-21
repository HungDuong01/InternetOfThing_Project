
import java.io.IOException;
import javafx.application.Platform;
import com.lloseng.ocsf.client.AbstractClient;

/*
LIGHT, LOCK, THERMOSTAT, CAMERA, WATER_SYSTEM, TEMPERATURE_INCREMENT, TEMPERATURE_DECREMENT,
LIGHT_STATUS, LIGHT_BRIGHTNESS_UPDATE, LIGHT_COLOR_UPDATE, WATER_USAGE, WATER_LIMIT, USER_INFO, LOCK_STATUS,
CAMERA_ANGLE

*/


public class SmartHomeClient extends AbstractClient {

	// create instance of GUIController class
	private GUIController controller;
	
	private int temp;
	
	// setter and getter method to handle the temperature from server
	public void setTempData(int temp)
	{
		this.temp = temp + 1;
		
	}
	
	public int getTempData()
	{
		return this.temp;
	}
	
	
	

	// Constructor with the parameters including the controller class
	public SmartHomeClient(String host, int port, GUIController controller) {
		super(host, port);
		this.controller = controller;
	
	}
	
	

	public void connectToServer()
	{
		try {
			//OCSF method inherited from AbstractClient Class to open connection to a server
			openConnection();
			System.out.println("Connected to server!");
			
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Error - Cannot connect to server!");
			
		}
		
	}
	
    public void sendEnumToServer(String messageType) {
        if (isConnected()) {
            try {
                sendToServer(messageType);
                System.out.println("Sent MessageType to server: " + messageType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Not connected to server.");
        }
    }
	
	
	// send to server
	
	public void temperatureIncrement() {
		// if client is connected to server do the following actions
		
	try{

	// Send message to decrease to the server
		sendToServer("increase");
		// Display message on the console after decrementing the temperature
		System.out.println("Sent temperature increase of " + getTempData() +  " request to server");
	} catch(IOException e)
	{
	// Error handle message
		e.printStackTrace();
		System.err.println("Error sending temperature decrease");

	}
}
	
	

	void  temperatureDecrement() throws IOException {
		try{

			// Send message to decrease to the server
			sendToServer("decrease");
			// Display message on the console after decrementing the temperature
			System.out.println("Sent temperature decrease of " + getTempData() +  " request to server");
		} catch(IOException e)
		{
			// Error handle message
			e.printStackTrace();
			System.err.println("Error sending temperature decrease");

		}
	}
	
	

	@Override
	protected void handleMessageFromServer(Object msg) {

	 if(msg instanceof Integer) {
		// handle temperature from server and set it.
		setTempData((int) msg);
		// Proper Asynchronous Execution. Set the label text on thermostat GUI
		Platform.runLater(() -> controller.setTextTemperature(getTempData()));
		}
	 	//Display message on console
		System.out.println("Received message: " +  getTempData());

	}
		

		
}


