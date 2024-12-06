import java.io.Serializable;
import java.util.regex.Pattern;

public class Demographics implements Serializable {
    private String name;
    private String race;
    private int age;
    private String address;
    private String contactInfo;

    // Constructor 
    public Demographics(String name, String race, int age, String address, String contactInfo) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.address = address;
        this.contactInfo = contactInfo;
    }

// age validator 
    public class AgeValidator {
    public static boolean isValidAge(int age) {
        return age >= 18;
    }
}

//  address validator
public class AddressValidator {
    public static boolean isValidAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        String addressPattern = "^[\\d]+[\\s]+[a-zA-Z0-9\\s.,'-]+( Apt [\\d]+)?$";
        return Pattern.matches(addressPattern, address);
    }
}

//  phone number validator
public class PhoneNumberValidator {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");
        return digitsOnly.length() >= 7 && digitsOnly.length() <= 10 && Pattern.matches("[0-9()\\- ]+", phoneNumber);
    }
}

    // Getters
    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // Setters 
    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}