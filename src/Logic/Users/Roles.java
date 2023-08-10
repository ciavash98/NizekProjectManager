package Logic.Users;

public enum Roles {

    SUPER_ADMIN("Super Admin"),
    PRODUCT_OWNER("Product Owner"),
    QUALITY_ASSURANCE("Quality Assurance"),
    DEVELOPER("Developer");

    String title;

    Roles(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public static Roles findByName(String name) {
        for (Roles item : values()) {
            if(item.title.equals(name)){
                return item;
            }
        }
        return null;
    }
}
