package datatypes;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    public static ArrayList<String> countries = new ArrayList<>();

}
