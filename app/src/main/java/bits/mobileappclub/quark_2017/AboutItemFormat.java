package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 1/2/17.
 */

public class AboutItemFormat {
    String name;
    String phone;
    String email;
    int id;
    String post;

    public AboutItemFormat(String name, String phone, String email, int id, String post) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.post = post;
    }

    public AboutItemFormat() {
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
