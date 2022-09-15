/**
 * 
 * HeartTransplant class
 * 
 * @author Ibtesaam Ahsan, ia317@rutgers.edu, 210005064
 */

public class HeartTransplant {
    private Patient[] patients;
    private SurvivabilityByAge survivabilityByAge;
    private SurvivabilityByCause survivabilityByCause;

    public HeartTransplant() {

        this.patients = null;
        this.survivabilityByAge = null;
        this.survivabilityByCause = null;
    }

    public Patient[] getPatients() {

        return patients;
     } 

    public SurvivabilityByAge getSurvivabilityByAge() {
        return survivabilityByAge;
    }

    public SurvivabilityByCause getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    public void readPatients (int numberOfLines) {
        
        patients = new Patient[numberOfLines];
        for(int i = 0; i < numberOfLines; i++)
        {
            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int soh = StdIn.readInt();
            patients[i] = new Patient(id, ethnicity, gender, age, cause, urgency, soh);
            
        }
        
    }

    public void readSurvivabilityByAge (int numberOfLines) {
        survivabilityByAge = new SurvivabilityByAge();
        for(int i = 0; i < numberOfLines; i++)
        {
            int age = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByAge.addData(age, yearsPostTransplant, rate);
        }
    }

    public void readSurvivabilityByCause (int numberOfLines) {
        survivabilityByCause = new SurvivabilityByCause();
        for(int i = 0; i < numberOfLines; i++)
        {
            int cause = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByCause.addData(cause, yearsPostTransplant, rate);
        }
    }
    
    public Patient[] getPatientsWithAgeAbove(int age) {
        int count = 0;
        for(int i = 0; i < patients.length; i ++)
        {
            if(patients[i].getAge() > age)
            {
                count++;
            }
        }
        if(count == 0)
            return null;
        Patient[] patientsAboveAge = new Patient[count];
        int patientIndex = 0;
        for(int i = 0; i < patients.length; i ++)
        {
            if(patients[i].getAge() > age)
            {
                patientsAboveAge[patientIndex] = patients[i];
                patientIndex++;
            }
        }
        
        return patientsAboveAge;
    }

    public Patient[] getPatientsByHeartConditionCause(int cause) {
        int count = 0;
        for(int i = 0; i < patients.length; i ++)
        {
            if(patients[i].getCause() == cause)
            {
                count++;
            }
        }

        if(count == 0)
            return null;
        Patient[] patientsWithCause = new Patient[count];
        int patientIndex = 0;
        for(int i = 0; i < patients.length; i ++)
        {
            if(patients[i].getCause() == cause)
            {
                patientsWithCause[patientIndex] = patients[i];
                patientIndex++;
            }
        }
        
        return patientsWithCause;
    }

    public Patient[] getPatientsByUrgency(int urgency) {

    int count = 0;
    for(int i = 0; i < patients.length; i ++)
    {
        if(patients[i].getUrgency() == urgency)
        {
            count++;
        }
    }

    if(count == 0)
        return null;
    Patient[] patientsWithUrgency = new Patient[count];
    int patientIndex = 0;
    for(int i = 0; i < patients.length; i ++)
    {
        if(patients[i].getUrgency() == urgency)
        {
            patientsWithUrgency[patientIndex] = patients[i];
            patientIndex++;
        }
    }
    
    return patientsWithUrgency;
    }

    public Patient getPatientForTransplant () {
    Patient patientForTP = patients[0];
    int index = 0;
	for(int i = 0; i < patients.length; i++)
    {
        if(patients[i].getNeedHeart() == true && patients[i].getUrgency() == 8)
        {
            int age = patients[i].getAge();
            if(age > 0 && age < 35)
            {
                if(patientForTP.getAge() > age)
                {
                    patientForTP = patients[i];
                    index = i;
                }
            }
            else if(age > 50 && age < 65)
            {
                if(patientForTP.getAge() > age)
                {
                    patientForTP = patients[i];
                    index = i;
                }
            }
            else
            {
                if(patientForTP.getAge() > age)
                {
                    patientForTP = patients[i];
                    index = i;
                }
            }
        }
    }
    patients[index].setNeedHeart(false);
	return patientForTP;
    }

}