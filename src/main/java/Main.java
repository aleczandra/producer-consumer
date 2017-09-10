import java.io.IOException;

import consumer.Teletype;
import model.Match;
import model.UpdatesQueue;
import producer.Reporter;

public class Main {

    public static void main(String [] args) throws IOException, InterruptedException {


        Match match1 = new Match("Match1");
        Reporter reporter1 = new Reporter("Reporter 1", match1, UpdatesQueue.getInstance());

        Match match2 = new Match("Match2");
        Reporter reporter2 = new Reporter("Reporter 2", match2, UpdatesQueue.getInstance());

        Teletype teletype = new Teletype(UpdatesQueue.getInstance());

        new Thread(reporter1).start();
        new Thread(reporter2).start();
        new Thread(teletype).start();

        Thread.sleep(70000);

        System.out.println("Matches Ended!");
        //Exit after few seconds ... this is only for  this example, so the program doesn't run forever
        System.exit(0);

    }
}
