package Model;

public class User {
        private int id;
        private String name;
        private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Model.address getAddress() {
        return address;
    }

    public void setAddress(Model.address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Model.company getCompany() {
        return company;
    }

    public void setCompany(Model.company company) {
        this.company = company;
    }

    private String email;
        private address address;
        private String phone;
        private String website;
        private company company;
}