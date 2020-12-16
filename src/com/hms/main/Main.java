/**
 * This the driver class where the project can launched.
 */
package com.hms.main;


import com.hms.abstractfactory.TransmitterFactory;
import com.hms.adapter.ExpertPrinter;
import com.hms.adapter.PrintAdapter;
import com.hms.adapter.Printer;
import com.hms.facade.ServicesFacade;
import com.hms.observer.ControlUnit;
import com.hms.observer.Device;
import com.hms.services.BasicServiceCollection;
import com.hms.services.Iterator;
import com.hms.services.Service;
import com.hms.services.ServiceCollection;
import com.hms.services.ServiceType;

public class Main {

  static TransmitterFactory transmitterFactory;

  public static void main(String[] args) {

    // Create a runner
    Runner runner = Runner.getInstance();
    runner.setRunnerName("John Doe");
    System.out.println("Runner: " + runner.getName());

    // Create a route
    Route.getInstance().setPoints(Util.pointsGenerator());

    // Create a Deluxe Service
    Service service = ServicesFacade.createService(ServiceType.DELUXE);
    runner.setService(service);
    service.setRunner(runner);


    // Adapter
    ExpertPrinter expertPrinter = new ExpertPrinter();
    Printer printer = new PrintAdapter(expertPrinter);
    ServicesFacade.getInstance().printService(printer, service);


    // Observer
    ControlUnit cu = new ControlUnit();
    Device device = new Device(cu);
    cu.attach(device);
    cu.ticks();

    // Measure Distance (Command Pattern)
    ServicesFacade.getInstance().measureDistance(runner);

    // Measure Blood Pressure (Command Pattern)
    ServicesFacade.getInstance().bloodPressure(runner);

    // Test Composite Pattern (Music Items PlayList)
    ServicesFacade.getInstance().buildMusicList();
    ServicesFacade.getInstance().browseMI();

    // Rebooting Devices (Template Design Pattern)
    service.getBelt().reboot();
    service.getWarch().reboot();
    
    ServiceCollection services = new BasicServiceCollection();
    services.addService(new Service("Measure BloodPressure", ServiceType.BASIC));
    services.addService(new Service("Measure Heart Rate", ServiceType.BASIC));
    services.addService(new Service("Measure Speed", ServiceType.BASIC));
    services.addService(new Service("Measure Distance", ServiceType.BASIC));
	/*channels.addChannel(new Channel(102.5, ChannelTypeEnum.HINDI));
	channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
	channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
	channels.addChannel(new Channel(105.5, ChannelTypeEnum.HINDI));
	channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));*/
	
	// Basic Type Iterator
	Iterator baseIterator = services.iterator(ServiceType.BASIC);
	while (baseIterator.hasNext()) {
		Service c = baseIterator.next();
		System.out.println(c.toString());
	}
	
	
	// Deluxe Type Iterator
	
}

  }
