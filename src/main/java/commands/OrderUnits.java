package commands;

import datatypes.Commands;
import datatypes.Data;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class OrderUnits extends Commands {
    @Override
    public String name() {
        return "orderunits";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) throws IOException {
        if (!Data.games.containsKey(event.getAuthor()))
            event.getMessage().reply("You are not in a lobby").queue();
        else {
            if (!isTurn(event.getAuthor()))
                event.getMessage().reply("It is not your turn").queue();
            else {
                int units = 0;
                try {
                    units = Integer.parseInt(args[1]);
                } catch(NumberFormatException ignored) {
                }

                if (units <= 0) {
                    event.getMessage().reply("Enter a positive integer amount of units to buy").queue();
                } else {
                    // TODO purchase %units% number of units for the player
                    event.getMessage().reply("Ordered " + units + " units (yeah definitely)").queue();
                }
            }
        }
    }

    private boolean isTurn(User user) {
        return true; // TODO implement isTurn() method
    }
}
