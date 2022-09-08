package Model;

public class address {
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCiti() {
        return citi;
    }

    public void setCiti(String citi) {
        this.citi = citi;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Model.geo getGeo() {
        return geo;
    }

    public void setGeo(Model.geo geo) {
        this.geo = geo;
    }

    private String suite;
    private String citi;
    private String zipcode;
    private geo geo;
}