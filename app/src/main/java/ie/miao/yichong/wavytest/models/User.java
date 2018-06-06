package ie.miao.yichong.wavytest.models;

/**
 * Author:   yichongmiao
 * CreateAt: 05/06/2018.
 */

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private String phoneNumber;

    public User(String id, String firstName, String lastName, String email, String profilePicture, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
