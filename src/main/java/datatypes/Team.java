package datatypes;

import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Team {
    User[] players;
    User host;
    public Team(User[] _players, User _host) {
        players = _players;
        host = _host;
    }
    public User[] getPlayers() {
        return players;
    }
    public User getHost() {
        return host;
    }

    public List<User> getUniquePlayers() {
        List<User> UniquePlayers = new ArrayList<User>();
        for (User u : getPlayers()) {
            if (!UniquePlayers.contains(u))
                UniquePlayers.add(u);
        }
        return UniquePlayers;
    }
}
