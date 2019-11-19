import model.Schedule;
import model.TransportCompany;
import util.EffectiveUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {

        if (args[0] == null || args[0].isEmpty()) {
            throw new RuntimeException("Wrong file path!");
        }

        List<String> fileStrings;
        try {
            fileStrings = Files.lines(Paths.get(args[0])).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        TreeSet<Schedule> grotty = new TreeSet<>();
        TreeSet<Schedule> posh = new TreeSet<>();
        for (String fileString : fileStrings) {
            String[] params = fileString.split(" ");
            if (TransportCompany.GROTTY_COMPANY.getName().startsWith(params[0])) {
                grotty.add(new Schedule(TransportCompany.GROTTY_COMPANY, LocalTime.parse(params[1]), LocalTime.parse(params[2])));
            } else if (TransportCompany.POSH_COMPANY.getName().startsWith(params[0])) {
                posh.add(new Schedule(TransportCompany.POSH_COMPANY, LocalTime.parse(params[1]), LocalTime.parse(params[2])));
            } else {
                System.err.printf("Cannot parse string: %s!\n", fileString);
            }
        }

        grotty = ((TreeSet<Schedule>) filterNotEffective(grotty));
        posh = ((TreeSet<Schedule>) filterNotEffective(posh));

        writeToFile(posh, grotty);
    }

    private static Set<Schedule> filterNotEffective(Set<Schedule> schedules) {
        Set<Schedule> result = new TreeSet<>();
        for (Schedule schedule : schedules) {
            if (EffectiveUtil.isEffective(schedule, schedules)) {
                result.add(schedule);
            }
        }

        return result;
    }

    private static void writeToFile(Set<Schedule> posh, Set<Schedule> grotty) {
        Path path = Paths.get(OUTPUT_FILENAME);
        try (final BufferedWriter writer = Files.newBufferedWriter(path))
        {

            for (Schedule schedule : posh) {
                writer.write(schedule + "\n");
            }

            writer.write("\n");

            for (Schedule schedule : grotty) {

                if (posh.contains(schedule)) continue;

                writer.write(schedule + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
