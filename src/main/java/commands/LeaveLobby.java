package commands;

import datatypes.Commands;
import datatypes.Data;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class LeaveLobby extends Commands {

    @Override
    public String name() {
        return "leavelobby";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) throws IOException {
        if (!Data.games.containsKey(event.getAuthor()))
            event.getMessage().reply("You are not a host of an existing lobby").queue();
        else {
            Data.games.remove(event.getAuthor());
            event.getMessage().reply("Your lobby has been deleted").queue();
        }
    }
}
