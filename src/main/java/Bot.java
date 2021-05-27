import commands.*;
import datatypes.Commands;
import datatypes.API;
import datatypes.Data;
import datatypes.LoadMap;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class Bot {
    static String prefix;
    public static HashMap<String, Commands> commands = new HashMap<String, Commands>();


    Bot(API bot, String _prefix) throws LoginException, IOException, ParseException {
        prefix = _prefix;
        build(bot);
        AddCommands();
        LoadMap.Generate();
    }

    void AddCommands() {
        add(new Ping());
        add(new CreateLobby());
        add(new InfoLobby());
        add(new LeaveLobby());
        add(new OrderUnits());
        //remove later
        add(new Debug());
        add(new DebugCountry())
;    }

    void add(Commands c) {
        commands.put(prefix+c.name(), c);
    }

    void build(API api) throws LoginException {
        JDABuilder.create(api.getToken(), EnumSet.allOf(GatewayIntent.class))
                .addEventListeners(new Events())
                .setStatus(api.getOnlineStatus())
                .setActivity(api.getActivity())
                .build();
    }

    static String getPrefix() {
        return prefix;
    }

}