import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;
import javax.swing.JPanel;

public class RepeatFormat 
{
    private static Font font; 
    
    public Font getTextFont()
    {
        return font = new Font("Georgia", Font.PLAIN, 20);
    }
    public Font getTitleFont()
    {
        return font = new Font("Georgia", Font.BOLD, 30);
    }

    public void showCard(JPanel parentPanel, String card)
    {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, card);
    }

    public String getDateDifference(LocalDate date1, LocalDate date2) 
    {
        // Compare the dates and calculate the difference
        if (date1.getYear() != date2.getYear()) 
        {
            // If the years are different, return the difference in years
            long yearsDifference = ChronoUnit.YEARS.between(date1, date2);
            return yearsDifference + " year(s)";
        } 
        else if (date1.getMonthValue() != date2.getMonthValue()) 
        {
            // If the years are the same but months are different, return the difference in months
            long monthsDifference = ChronoUnit.MONTHS.between(date1, date2);
            return monthsDifference + " month(s)";
        } 
        else 
        {
            // If the years and months are the same but days are different, return the difference in days
            long daysDifference = ChronoUnit.DAYS.between(date1, date2);
            return daysDifference + " day(s)";
        }
    }

    //Method to check if a string with a phone number is a valid phone number
    public boolean isValidPhoneNumber(String phoneNumber) 
    {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");
        return digitsOnly.length() >= 7 && digitsOnly.length() <= 10 && Pattern.matches("[0-9()\\- ]+", phoneNumber);
    }

    //Method to check if a string with an address is a valid address
    public boolean isValidAddress(String address) 
    {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        String addressPattern = "^[\\d]+[\\s]+[a-zA-Z0-9\\s.,'-]+( Apt [\\d]+)?$";
        return Pattern.matches(addressPattern, address);
    }
}
