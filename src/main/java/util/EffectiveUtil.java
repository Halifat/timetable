package util;

import model.Schedule;

import java.time.temporal.ChronoUnit;
import java.util.Set;

public class EffectiveUtil {

    private EffectiveUtil() {

    }

    public static Boolean isEffective(Schedule scheduleCheck, Set<Schedule> schedules) {
        long hoursBetween = ChronoUnit.HOURS.between(scheduleCheck.getStarts(), scheduleCheck.getReaches());

        if (hoursBetween > 1) return false;

        for (Schedule schedule : schedules) {

            if (schedule.equals(scheduleCheck)) continue;

            if (scheduleCheck.getStarts().equals(schedule.getStarts())
                    && !schedule.getReaches().isAfter(scheduleCheck.getReaches())) {
                return false;
            }

            if (scheduleCheck.getReaches().equals(schedule.getReaches()) && !scheduleCheck.getStarts().isAfter(schedule.getStarts())) {
                return false;
            }

            if (scheduleCheck.getReaches().isAfter(schedule.getReaches()) && scheduleCheck.getStarts().isBefore(schedule.getStarts())) {
                return false;
            }
        }

        return true;
    }
}
