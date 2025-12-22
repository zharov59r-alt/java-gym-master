package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Coach coach;
    private Integer trainingCount;

    public CounterOfTrainings(Coach coach, int trainingCount) {
        this.coach = coach;
        this.trainingCount = trainingCount;
    }

    public Coach getCoach() {
        return coach;
    }

    public Integer getTrainingCount() {
        return trainingCount;
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        return o.trainingCount - this.trainingCount;
    }


}
