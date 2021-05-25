package commands;

import datatypes.Commands;
import datatypes.Data;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class Debug extends Commands {

    @Override
    public String name() {
        return "print";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) throws IOException {
        event.getMessage().reply(Data.ix_to_name+"").queue();
        event.getMessage().reply(Data.name_to_ix+"").queue();
        event.getMessage().reply(Data.connections+"").queue();
    }
}
