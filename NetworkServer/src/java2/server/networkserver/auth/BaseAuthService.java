package java2.server.networkserver.auth;

import java.util.*;


public class BaseAuthService implements AuthService {

    private static class AuthEntry {
        private String login;
        private String password;

        public AuthEntry(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthEntry authEntry = (AuthEntry) o;
            return Objects.equals(login, authEntry.login) &&
                    Objects.equals(password, authEntry.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(login, password);
        }
    }

    public static final Map  <AuthEntry, String> NICK_BY_LOGIN_AND_PASSWORD = new HashMap<>();

    private static void fillMap () {
        for (int i = 0; i < 5; i++) {
            int number = i + 1;
            NICK_BY_LOGIN_AND_PASSWORD.put(new AuthEntry("login" + number, "pass" + number), "nick" + number);
        }
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        fillMap();
        return NICK_BY_LOGIN_AND_PASSWORD.get(new AuthEntry (login, password));
    }

    @Override
    public void start() {
        System.out.println("Auth service has been started");

    }

    @Override
    public void stop() {
        System.out.println("Auth service has been stopped");

    }
}
