package Logic.Users;

public enum UserError {

    PASSWORD_NOT_MATCH,
    EMAIL_NOT_VALID,
    NAME_NOT_VALID,
    WRONG_USER_OR_PASSWORD,
    EMAIL_DOSE_ALREADY_REGISTERED;

    @Override
    public String toString() {
        return switch (this) {
            case PASSWORD_NOT_MATCH -> "Passwords dose not match.";
            case EMAIL_NOT_VALID -> "Enter a valid Email address.";
            case NAME_NOT_VALID -> "Fullname should be A-Z.";
            case WRONG_USER_OR_PASSWORD -> "Email or password is Wrong.";
            case EMAIL_DOSE_ALREADY_REGISTERED -> "This email already registered";
        };
    }
}
