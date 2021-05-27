package datatypes;

import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Team team;
    int Turn;
    Country[] countries;    //countries on the board

    public Game(Team _team) {
        team = _team;

        List<User> UniquePlayers = team.getUniquePlayers();
        for (User u : UniquePlayers) {              //players -> host
            Data.getHost.put(u, team.getHost());
        }
        countries = new Country[Data.name_to_ix.size()];
        //hardcode del later
        countries[0] = (new Country(0, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    public Country getCountry(int n) {
        return countries[n];
    }

    public Team getTeam() {
        return team;
    }
}
