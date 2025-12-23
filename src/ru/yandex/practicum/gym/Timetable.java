package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании

        if (timetable.containsKey(trainingSession.getDayOfWeek())) {
            if (timetable.get(trainingSession.getDayOfWeek()).containsKey(trainingSession.getTimeOfDay())) {
                timetable.get(trainingSession.getDayOfWeek()).get(trainingSession.getTimeOfDay()).add(trainingSession);
            } else {
                timetable.get(trainingSession.getDayOfWeek()).put(trainingSession.getTimeOfDay(), new ArrayList<>() {{ add(trainingSession);
                }
                });
            }
        } else {
            timetable.put(
                trainingSession.getDayOfWeek(),
                new TreeMap<>(Map.of(trainingSession.getTimeOfDay(), new ArrayList<>() {{ add(trainingSession);
                }
                })));
        }

    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return timetable.get(dayOfWeek);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        return timetable.get(dayOfWeek).get(timeOfDay);
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
