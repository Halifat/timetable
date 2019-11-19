package model;

public enum TransportCompany {
    POSH_COMPANY("Posh"),
    GROTTY_COMPANY("Grotty");

    private String name;

    TransportCompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TransportCompany findCompany(String name) {

        for (TransportCompany transportCompany : TransportCompany.values()) {
            if (transportCompany.name.equalsIgnoreCase(name)) {
                return transportCompany;
            }
        }

        return null;
    }
}
