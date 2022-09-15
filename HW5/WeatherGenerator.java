package HW5;

public class WeatherGenerator {

    static final int WET = 1; 
    static final int DRY = 2; 
    static final int[] numberOfDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    

    public static void populateArrays(double[][] drywet, double[][] wetwet) {

        StdIn.setFile("drywet.txt");

    for(int i=0; i < drywet.length; i++){
            for(int j=0; j<14; j++){
                drywet[i][j] = StdIn.readDouble();
            }
        }

    StdIn.setFile("wetwet.txt");

    for(int i=0; i < wetwet.length; i++){
            for(int j=0; j<14; j++){
                wetwet[i][j] = StdIn.readDouble();
            }
        }
    }


    public static void populateLocationProbabilities( double[] drywetProbability, double[] wetwetProbability, 
                                     double longitude, double latitude, 
                                     double[][] drywet, double[][] wetwet){
        
        for(int i=0; i < drywet.length; i++){
            if(drywet[i][0] == longitude && drywet[i][1] == latitude)
            {
                for(int j = 0; j < 12; j ++)
                {
                    drywetProbability[j] = drywet[i][j+2];
                }
            }
        }

        for(int i=0; i < wetwet.length; i++){
            if(wetwet[i][0] == longitude && wetwet[i][1] == latitude)
            {
                for(int j = 0; j < 12; j ++)
                {
                    wetwetProbability[j] = wetwet[i][j+2];
                }
            }
        }
        
    }

    public static int[] forecastGenerator( double drywetProbability, double wetwetProbability, int numberOfDays) {
        double rand = StdRandom.uniform();
        int[] fcast = new int[numberOfDays];
        if( rand < 0.5)
            fcast[0] = WET;
        
        else 
            fcast[0] = DRY; 

        for(int i = 1; i < numberOfDays; i++)
        {
            rand = StdRandom.uniform();

            if(fcast[i-1] == WET)  
            {     
                if(rand <= wetwetProbability) 
                    fcast[i] = WET;
                else
                    fcast[i] = DRY;
            }
            else
            {
                if(rand <= drywetProbability)
                    fcast[i] = WET;
                else
                    fcast[i] = DRY;
            }
        }
        return fcast;
    }
    public static int[] oneMonthForecast(int numberOfLocations, int month, double longitude, double latitude ){
        double[][] drywet = new double[numberOfLocations][14];
        double[][] wetwet = new double[numberOfLocations][14];
        populateArrays(drywet, wetwet);
        double[] drywetProbability = new double[12];
        double[] wetwetProbability = new double[12];
        populateLocationProbabilities(drywetProbability, wetwetProbability, longitude, latitude, drywet, wetwet);
        int[] monthForcast = forecastGenerator(drywetProbability[month], wetwetProbability[month], numberOfDaysInMonth[month]); 
        return monthForcast;
    }

    public static int numberOfWetDryDays (int[] forecast, int mode) {
        
        int count = 0;
        for(int i = 0; i < forecast.length; i++)
        {
            if(forecast[i] == mode)
                count++;
        }
        return count;
    }

    public static int lengthOfLongestSpell (int[] forecast, int mode) {
        
        int consecDays = 0;
        int hold = 0;
        int count = 0;
        for(int i = 0; i < forecast.length; i++)
        {
            
            if(forecast[i] == mode) 
            {
                count++;
                hold = count;
            }
            else
            {
                if(consecDays < count)
                {
                    consecDays = count;
                }
                count = 0;
            }
            
        }
        if (count != 0 && consecDays < hold)
            consecDays = count;
        return consecDays;
    }


    public static int bestWeekToTravel(int[] forecast){
        int weekIndex = 0;
        int count = 0;
        int hold = 0;
        
        for(int i = 0; i< forecast.length-6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(forecast[i+j] == DRY)
                {
                    count++;
                    if(count == 7)
                        return i;
                }
                
            }
            if(hold < count)
            {
                weekIndex = i;
                hold = count;
            }
                
            count = 0;
        }

        return weekIndex;
    }
    public static void main (String[] args) {
        StdRandom.setSeed(1636074644380L);
        int numberOfRows    = 4100; 
        int numberOfColumns = 14;   
        
        double longitude = Double.parseDouble(args[0]);
        double latitude  = Double.parseDouble(args[1]);
        int    month     = Integer.parseInt(args[2]);
        
        int[] forecast = oneMonthForecast( numberOfRows, month,  longitude,  latitude );
        
        
        int drySpell = lengthOfLongestSpell(forecast, DRY);
        int wetSpell = lengthOfLongestSpell(forecast, WET);
        int bestWeek = bestWeekToTravel(forecast);

        StdOut.println("There are " + forecast.length + " days in the forecast for month " + month);
        StdOut.println(drySpell + " days of dry spell.");
        StdOut.println("The bestWeekToTravel starts on:" + bestWeek );

        for ( int i = 0; i < forecast.length; i++ ) {
            String weather = (forecast[i] == WET) ? "Wet" : "Dry";  
            StdOut.println("Day " + (i) + " is forecasted to be " + weather);
        }
    }
}