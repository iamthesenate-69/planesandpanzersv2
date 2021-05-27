package commands;

import datatypes.Commands;
import datatypes.Data;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class DebugCountry extends Commands {

    @Override
    public String name() {
        return "printtroop";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) throws IOException {
        int countryType = Data.name_to_ix.get(args[1]);
        int majorPower = Integer.parseInt(args[2]);
        int troopType = Integer.parseInt(args[3]);
        event.getMessage().reply(Data.games.get(Data.getHost.get(event.getAuthor())).getCountry(countryType).getMajorPower(majorPower).getTroopCount(troopType)+"").queue();
    }
}
