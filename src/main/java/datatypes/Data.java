package datatypes;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

    public final static String countries[] = {"Germany", "US", "Russia", "UK", "Japan"};

    //before game is started
    public static HashMap<Message, Team> reactionCheck = new HashMap<Message, Team>();

    //during the game
    public static HashMap<User, Game> games = new HashMap<User, Game>();
    public static HashMap<User, List<User>> getPartyByHost = new HashMap<User, List<User>>();
    public static HashMap<User, User> getHost = new HashMap<User, User>();

    public static class Game {
        Team team;
        public Game(Team _team) {
            team = _team;

            List<User> UniquePlayers = team.getUniquePlayers();
            for (User u : UniquePlayers) {              //players -> host
                getHost.put(u, team.getHost());
            }
            getPartyByHost.put(team.getHost(), UniquePlayers);    //host -> players
        }

        public Team getTeam() {
            return team;
        }

    }
    public static class Team {
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

}
