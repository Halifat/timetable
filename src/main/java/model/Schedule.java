package model;

import java.time.LocalTime;
import java.util.Objects;

public class Schedule implements Comparable<Schedule> {
    private TransportCompany company;
    private LocalTime starts;
    private LocalTime reaches;

    public Schedule() {

    }

    public Schedule(TransportCompany company, LocalTime starts, LocalTime reaches) {
        this.company = company;
        this.starts = starts;
        this.reaches = reaches;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    public LocalTime getStarts() {
        return starts;
    }

    public void setStarts(LocalTime starts) {
        this.starts = starts;
    }

    public LocalTime getReaches() {
        return reaches;
    }

    public void setReaches(LocalTime reaches) {
        this.reaches = reaches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(starts, schedule.starts) &&
                Objects.equals(reaches, schedule.reaches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(starts, reaches);
    }

    @Override
    public int compareTo(Schedule o) {
        return this.starts.compareTo(o.starts);
    }

    @Override
    public String toString() {
        return company.getName() + " " + starts.toString() + " " + reaches.toString();
    }
}
