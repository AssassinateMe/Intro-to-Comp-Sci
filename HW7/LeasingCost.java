package HW7;

public class LeasingCost {

	public static Vehicle[] createAllVehicles(String filename) {
        StdIn.setFile(filename);

        int numberOfCars = Integer.parseInt( StdIn.readLine() );
        Vehicle[] vehicles = new Vehicle[numberOfCars];

        for ( int i = 0; i < numberOfCars; i++ ) {
        	String line = StdIn.readLine();
            vehicles[i] = createVehicle(line);
        }
        return vehicles;
    }

	public static double computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit ){
		double miles = numberOfMonth * mileageAllowance ;
		return miles/usage*co2PerUnit;
    }

	public static double computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice){
    	double miles = numberOfMonth * mileageAllowance;
        double cost = miles/usage * fuelPrice;
    	return cost;
    }

	public static double computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost){
    	
        return dueAtSigning + numberOfMonths*monthlyCost;
    }

	public static Vehicle createVehicle(String description)
    {
        String n = "name:"; 
        String t = "type:"; 
        String d = "due:"; 
        String l = "length:"; 
        String m = "monthly:";
        String mpu = "mile/unit:"; 
        String a = "allowance:"; 
        String c = "charger:";
        
        String pholder = description.substring(description.indexOf(n));
        String name = pholder.substring(n.length(), pholder.indexOf(';'));

        pholder = description.substring(description.indexOf(t));
        String type = pholder.substring(t.length(), pholder.indexOf(';'));

        pholder = description.substring(description.indexOf(d));
        String due = pholder.substring(d.length(), pholder.indexOf(';'));
        double dues = Double.parseDouble(due);

        pholder = description.substring(description.indexOf(l));
        String length = pholder.substring(l.length(), pholder.indexOf(';'));
        int len = Integer.parseInt(length);

        pholder = description.substring(description.indexOf(m));
        String monthly = pholder.substring(m.length(), pholder.indexOf(';'));
        double mon = Double.parseDouble(monthly);

        pholder = description.substring(description.indexOf(mpu));
        String mileunit = pholder.substring(mpu.length(), pholder.indexOf(';'));
        double mileU = Double.parseDouble(mileunit);

        pholder = description.substring(description.indexOf(a));
        String allowance = pholder.substring(a.length(), pholder.indexOf(';'));
        int allow = Integer.parseInt(allowance);

        Fuel fuel;
        if(type.equals("electric"))
        {
            pholder = description.substring(description.indexOf(c));
            String charger = pholder.substring(c.length(), pholder.indexOf(';'));
            fuel = new Fuel(mileU, Double.parseDouble(charger));

        }
        else
        {
            fuel = new Fuel(mileU);
        }

        Lease lease = new Lease(dues, len, mon, allow);
        
        Vehicle car = new Vehicle(name, fuel, lease);
	    return car;
	}

	public static void computeCO2EmissionsAndCost( Vehicle[] vehicles, double gasPrice, double electricityPrice )
    {
        for(int i = 0; i < vehicles.length; i++)
        {
            Lease lease = vehicles[i].getLease();
            Fuel fuel = vehicles[i].getFuel();
            double due = lease.getDueAtSigning();
            double monthlyCost = lease.getMonthlyCost();
            double months = lease.getLeaseLength();
            double use = fuel.getUsage();
            double mileage = lease.getMileageAllowance();
            double type = fuel.getType();
            
            if(type == 1) // gas
            {
                vehicles[i].setCO2Emission(computeCO2(months, use, mileage, 8.887));
                vehicles[i].setFuelCost(computeFuelCost(months, use, mileage, gasPrice));
                vehicles[i].setTotalCost(computeLeaseCost(due, (int)months, monthlyCost) + vehicles[i].getFuelCost());
            }

            else // electric
            {
                vehicles[i].setCO2Emission(computeCO2(months, use, mileage, 0.453));
                vehicles[i].setFuelCost(computeFuelCost(months, use, mileage, electricityPrice));
                vehicles[i].setTotalCost(fuel.getCharger() + computeLeaseCost(due, (int)months, monthlyCost) + vehicles[i].getFuelCost());
            }
        }
       
    }

	public static void main (String[] args) {
        String filename         = args[0];
        double gasPrice 		= Double.parseDouble( args[1] );
		double electricityPrice = Double.parseDouble( args[2] );

		Vehicle[] vehicles = createAllVehicles(filename); 
		computeCO2EmissionsAndCost(vehicles, gasPrice, electricityPrice);

		for ( Vehicle v : vehicles ) 
            System.out.println(v.toString());
    }
}
