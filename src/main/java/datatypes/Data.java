package datatypes;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Data {

    //before game is started
    public static HashMap<Message, Team> reactionCheck = new HashMap<Message, Team>();

    //during the game
    public static HashMap<User, Game> games = new HashMap<User, Game>();
    public static HashMap<User, User> getHost = new HashMap<User, User>();

    //global stuff
    public static final String landPath = "./assets/landconnections.txt";
    public static HashMap<Integer, List<Integer>> connections = new HashMap<Integer, List<Integer>>();
    public static HashMap<String, Integer> name_to_ix = new HashMap<String, Integer>(); //andrej karpathy reference!!!11
    public static HashMap<Integer, String> ix_to_name = new HashMap<Integer, String>();
    public static List<String> countries = new ArrayList<>();

    public static void LoadMap() throws IOException {
        generateIndices();
        generateConnections();
    }

    private static void generateConnections() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(landPath));
        String row;
        while ((row = br.readLine()) != null) {
            String[] data = row.split(",");
            connections.put(name_to_ix.get(data[0]), toIXList(removeFirst(data)));  //this part is messy but watever
        }
    }

    private static List<Integer> toIXList(String[] data) {
        List<Integer> connections = new ArrayList<>();
        for (String s : data) {
            connections.add(name_to_ix.get(s));
        }
        return connections;
    }

    private static String[] removeFirst(String[] data) {
        return Arrays.copyOfRange(data, 1, data.length);
    }

    private static void generateIndices() throws IOException {
        String[] names = Files.readString(Paths.get(landPath), StandardCharsets.US_ASCII).replace("\r\n", ",").split(",");
        int i = 0;
        for (String country : names) {
            if (!countries.contains(country)) {
                countries.add(country);         // adds and assigns every unique country to a index (for faster search algorithms)
                name_to_ix.put(country, i);
                ix_to_name.put(i, country);
                i++;
            }
        }
    }


    public static class Game {
        Team team;
        public Game(Team _team) {
            team = _team;

            List<User> UniquePlayers = team.getUniquePlayers();
            for (User u : UniquePlayers) {              //players -> host
                getHost.put(u, team.getHost());
            }
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
