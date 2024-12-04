import java.awt.*;

public class HoldFont 
{
    private static Font font; 
    
    public Font getTextFont()
    {
        return font = new Font("Georgia", Font.PLAIN, 24);
    }
    public Font getTitleFont()
    {
        return font = new Font("Georgia", Font.BOLD, 30);
    }
}
