import datatypes.Data;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;


public class Events extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User u = event.getAuthor();
        if (u.isBot()) return;

        Message msg = event.getMessage();
        String[] split = msg.getContentRaw().split("( +)");
        String key = split[0];

        if (key.startsWith(Bot.getPrefix()) && Bot.commands.containsKey(key)) {
            try { Bot.commands.get(key).execute(event, split);
            } catch (Exception e) {
                event.getChannel().sendMessage("An error occurred: "+e.toString()).queue();
            }
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        Message msg = event.retrieveMessage().complete();

        if (Data.reactionCheck.containsKey(msg)) {
            Data.Team team = Data.reactionCheck.get(msg);

            User[] playersInParty = team.getPlayers();
            List<User> usersReacted = getUsersReacted(msg);

            if (ListContains(playersInParty, usersReacted)) {
                msg.reply("Poggers").queue();
                Data.reactionCheck.remove(msg);
                Data.games.put(team.getHost(), new Data.Game(team));
            }
        }
    }

    private List<User> getUsersReacted(Message msg) {
        List<User> usersReacted = new ArrayList<>();
        for (MessageReaction mr : msg.getReactions()) {
            for (User u : mr.retrieveUsers()) {
                usersReacted.add(u);
            }
        }
        return usersReacted;
    }

    private boolean ListContains(User[] playersInParty, List<User> usersReacted) {
        for (User u : playersInParty) {
            if (!usersReacted.contains(u))
                return false;
        }
        return true;
    }
}
