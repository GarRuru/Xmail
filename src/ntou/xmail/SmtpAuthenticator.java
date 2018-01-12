package ntou.xmail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {

    private String USER;
    private String PASSWORD;

    //no-argument constructor
    public SmtpAuthenticator() {

        super();
    }

    //GET Account detail
    public SmtpAuthenticator(String user, String password) {
        this();
        this.USER = user;
        this.PASSWORD = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String username = USER;
        String password = PASSWORD;
        if ((username != null) && (username.length() > 0)
                && (password != null)
                && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }

}
