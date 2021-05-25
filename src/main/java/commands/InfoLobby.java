package commands;

import datatypes.Commands;
import datatypes.Data;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class InfoLobby extends Commands {
    @Override
    public String name() {
        return "infolobby";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) throws IOException {
        if (!Data.games.containsKey(event.getAuthor()))
            event.getMessage().reply("You are not in a lobby").queue();
        else {
            User host = Data.getHost.get(event.getAuthor());
            User[] players = Data.games.get(host).getTeam().getPlayers();

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(String.format("%s's game", host.getName()));
            eb.setDescription(String.format("Germany: %s\nUS: %s\nRussia: %s\nUK: %s\nJapan: %s\n", players[0], players[1], players[2], players[3], players[4]));
            event.getMessage().reply(eb.build()).queue();
        }
    }
}
