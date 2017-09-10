package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Match {

    String name;

    List<UpdateMessage> updates;


    public Match(String name) throws IOException {
        this.name = name;
        this.updates = new ArrayList<>();
        createUpdateList();
        Collections.sort(updates);
    }


    public void createUpdateList() throws IOException {
        //read the entries from a file corresponding to the match name
        List<String> lines = readUpdatesFromFile();
        //process the entries from the file and transform them into UpdateMessage objects
        lines.forEach(l -> {
            String extractedTime = extractTime(l);
            long parsedTime = parseTimeToMilliseconds(extractedTime);
            String text = extractText(l);
            updates.add(new UpdateMessage(name, parsedTime, text, extractedTime));
        });

    }

    private String extractTime(String u) {
        return u.substring(0,u.indexOf(' '));
    }

    private long parseTimeToMilliseconds(String timeString) {
//        LocalTime lt = LocalTime.parse ( timeString );
//        Duration d = Duration.between ( LocalTime.MIN , lt );
//        return d.get(ChronoUnit.SECONDS);

        String[] split = timeString.split(":");

        long minutes = (Long.valueOf(split[0]) * 1000);

        long seconds = (Long.valueOf(split[1])) * 1000 / 60;

        long time = minutes + seconds;
        return time;
    }

    private String extractText(String u) {
        return u.substring(u.indexOf(' ') + 1);
    }

    public List<String> readUpdatesFromFile() throws IOException {
        String path = getClass().getClassLoader().getResource("updatemessages.txt").getPath();
        return Files.lines(Paths.get(path)).map(l -> l.toLowerCase()).filter(l -> l.contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UpdateMessage> getUpdates() {
        return Collections.unmodifiableList(updates);
    }

    public void setUpdates(List<UpdateMessage> updates) {
        this.updates = updates;
    }
}
