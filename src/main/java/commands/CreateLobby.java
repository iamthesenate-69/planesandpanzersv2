package commands;

import datatypes.Commands;
import datatypes.Data;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class CreateLobby extends Commands {

    @Override
    public String name() {
        return "createlobby";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) throws IOException {
        if (Data.games.containsKey(event.getAuthor()))
            event.getMessage().reply("You are already in a Lobby").queue();
        else {
            // createlobby germany us russia uk japan
            JDA instance = event.getJDA();
            User host = event.getAuthor();

            User[] players = getUsers(args, instance);

            //clean later
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(String.format("%s's game", host.getName()));
            eb.setDescription(String.format("Germany: %s\nUS: %s\nRussia: %s\nUK: %s\nJapan: %s\n", players[0], players[1], players[2], players[3], players[4]));
            eb.setFooter("Every player react to this message to start");

            event.getMessage().reply(eb.build()).queue(m ->
                    Data.reactionCheck.put(m, new Data.Team(players, host)));
        }

    }


    private User[] getUsers(String[] args, JDA instance) {
        User[] players = new User[args.length-1];   //skip args[0]; it is the command
        for (int i = 0; i < args.length-1; i++) {
            players[i] = instance.getUserById(args[i+1]);
        }
        return players;
    }

}
