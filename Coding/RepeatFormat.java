import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
}
