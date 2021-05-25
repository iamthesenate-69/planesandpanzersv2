import datatypes.API;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, LoginException, ParseException {
        String token = new Scanner(new File("./assets/token.txt")).nextLine(); // put a file named token.txt in directory
        API roosevelt  = new API(token, Activity.streaming("Ju 87", "https://www.youtube.com/watch?v=yXLl4V78-iI"), OnlineStatus.DO_NOT_DISTURB);
        Bot b = new Bot(roosevelt, "=");
    }
}
