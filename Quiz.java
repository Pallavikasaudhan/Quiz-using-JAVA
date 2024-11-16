import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        loadQuestions();
        currentQuestionIndex = 0;
        score = 0;
    }

    private void loadQuestions() {
    questions.add(new Question("What is the capital of France?", new String[]{"Paris", "Rome", "Berlin", "Madrid"}, 0));
    questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1));
    questions.add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "NaCl", "CO2"}, 0));
    questions.add(new Question("How many continents are there on Earth?", new String[]{"5", "6", "7", "8"}, 2));
    questions.add(new Question("Which animal is known as the King of the Jungle?", new String[]{"Elephant", "Lion", "Tiger", "Giraffe"}, 1));
}


    public Question getNextQuestion() {
    if (currentQuestionIndex < questions.size()) {
        return questions.get(currentQuestionIndex++);
    }
    return null;
}


    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
