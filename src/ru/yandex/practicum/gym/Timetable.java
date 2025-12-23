package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании

        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        if (timetable.containsKey(dayOfWeek)) {

            TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTimetable = timetable.get(dayOfWeek);

            if (dayTimetable.containsKey(timeOfDay)) {
                dayTimetable.get(timeOfDay).add(trainingSession);
            } else {
                ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
                trainingSessions.add(trainingSession);
                dayTimetable.put(timeOfDay, trainingSessions);
            }

        } else {

            TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTimetable = new TreeMap<>();
            ArrayList<TrainingSession>  trainingSessions = new ArrayList<>();

            trainingSessions.add(trainingSession);
            dayTimetable.put(timeOfDay, trainingSessions);

            timetable.put(dayOfWeek, dayTimetable);

        }

    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return timetable.getOrDefault(dayOfWeek, new TreeMap<>());
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        return timetable.getOrDefault(dayOfWeek, new TreeMap<>()).getOrDefault(timeOfDay, new ArrayList<>());
    }

    public ArrayList<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> trainingCount = new HashMap<>();
        ArrayList<CounterOfTrainings> counterOfTrainings = new ArrayList<>();

        for (var dayTrainings : timetable.values()) {
            for (var timeTrainings : dayTrainings.values()) {
                for (var trainingSession : timeTrainings) {
                    trainingCount.put(trainingSession.getCoach(), trainingCount.getOrDefault(trainingSession.getCoach(), 0) + 1);
                }
            }
        }

        for (var entry : trainingCount.entrySet()) {
            counterOfTrainings.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        Collections.sort(counterOfTrainings);

        return counterOfTrainings;

    }

}
