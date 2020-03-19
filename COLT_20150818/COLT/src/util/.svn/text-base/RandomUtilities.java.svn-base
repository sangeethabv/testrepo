package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;   
import java.util.Date;   
import java.util.Random;   

import org.apache.poi.hssf.record.formula.functions.Match;
   
public final class RandomUtilities   
{   
   

    private static String[] words = new String[]{"Lorem", //$NON-NLS-1$   
        "Ipsum", //$NON-NLS-1$   
        "Dolor", //$NON-NLS-1$   
        "Sit", //$NON-NLS-1$   
        "Smet", //$NON-NLS-1$   
        "Consetetur", //$NON-NLS-1$   
        "Sadipscing", //$NON-NLS-1$   
        "Elitr", //$NON-NLS-1$   
        "Sed", //$NON-NLS-1$   
        "Diam", //$NON-NLS-1$   
        "Nonumy", //$NON-NLS-1$   
        "Eirmod", //$NON-NLS-1$   
        "Tempor", //$NON-NLS-1$   
        "Invidunt", //$NON-NLS-1$   
        "Ut", //$NON-NLS-1$   
        "Labore", //$NON-NLS-1$   
        "Et", //$NON-NLS-1$   
        "Dolore", //$NON-NLS-1$   
        "Magna", //$NON-NLS-1$   
        "Aliquyam", //$NON-NLS-1$   
        "Erat", //$NON-NLS-1$   
        "Sed", //$NON-NLS-1$   
        "Diam", //$NON-NLS-1$   
        "Voluptua", //$NON-NLS-1$   
        "At", //$NON-NLS-1$   
        "Vero", //$NON-NLS-1$   
        "Eos", //$NON-NLS-1$   
        "Accusam", //$NON-NLS-1$   
        "Justo", //$NON-NLS-1$   
        "Duo", //$NON-NLS-1$   
        "Dolores", //$NON-NLS-1$   
        "Et", //$NON-NLS-1$   
        "Ea", //$NON-NLS-1$   
        "Rebum", //$NON-NLS-1$   
        "Stet", //$NON-NLS-1$   
        "Clita", //$NON-NLS-1$   
        "Kasd", //$NON-NLS-1$   
        "Gubergren", //$NON-NLS-1$   
        "No", //$NON-NLS-1$   
        "Sea", //$NON-NLS-1$   
        "Takimata", //$NON-NLS-1$   
        "Sanctus", //$NON-NLS-1$   
        "Est"}; //$NON-NLS-1$   
   
    private static String[] upper = new String[] {"A","B","C","D","F","G","H",
    	"J","K","L","M","N","P","Q","R","S","T","V","W","X","Z"};
    private static String[] upperA = new String[] {"A"};
    private static String[] specialchars = new String[] {"$",".","-","<","+","!","|"};
    
    private static String[] pass = new String[] {"cat", "dog", "horse", "lion", "monkey", "giraffe", "tortoise", "eagle"};    
    /**  
     * random number producer.  
     */   
    public static Random random = new Random();   
   
    /**  
     * utility class, don't instantiate.  
     */   
    private RandomUtilities()   
    {   
        super();   
    }   
   
    /**  
     * returns a random word.  
     * @return random word  
     */   
    public static String getRandomWord()   
    {   
        return words[random.nextInt(words.length)];   
    }   
    public static String getRandomPass()   
    {   
        return words[random.nextInt(pass.length)];   
    }   
    
    public static String getRandomUpperCase()
    {
    	return upper[random.nextInt(upper.length)];  	 
    }
    public static String getRandomUpperA()
    {
    	return upper[random.nextInt(upperA.length)];  	 
    }
    public static String getRandomSpecialChar()
    {
    	return specialchars[random.nextInt(specialchars.length)];  	 
    }
    
    public static Integer getRandomNumber()
    {
    	 return random.nextInt(10);
    }
    /**  
     * returns a random email.  
     * @return random email  
     */   
    public static String getRandomEmail()   
    {   
        return getRandomWord() + "@" + getRandomWord() + ".com"; //$NON-NLS-1$ //$NON-NLS-2$   
    }   
   
    /**  
     * returns a random date.  
     * @return random date  
     */   
    public static Date getRandomDate() 	  
    {   
   
        Calendar calendar = Calendar.getInstance();   
        calendar.add(Calendar.DATE, 365 - random.nextInt(730));   
        return calendar.getTime();   
    }   
    
    public static Integer getRandom4DigitNumber()
   {
   	 return (random.nextInt(8999)+1000);
   }
    
    public static Double Rounding2Decimals(double number)
    	{
          return Math.rint(number*100)/100;
    	}
    public static String getCurrentDate()
    {
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	Calendar calendar = Calendar.getInstance();
    	return dateFormat.format(calendar.getTime()).toString();
    }
}   