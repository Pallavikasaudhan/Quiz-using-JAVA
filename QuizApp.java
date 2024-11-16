import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame {
    private Quiz quiz;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel scoreLabel;

    public QuizApp() {
        quiz = new Quiz();

        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel("Question");
        add(questionLabel);

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            add(optionButtons[i]);
            int index = i;
            optionButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkAnswer(index);
                }
            });
        }

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        showNextQuestion();
    }

    private void showNextQuestion() {
    Question question = quiz.getNextQuestion();  // Get the next question
    if (question != null) {  // Check if there’s a question to display
        questionLabel.setText(question.getQuestionText());  // Show question text
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setEnabled(true);  // Enable options for answer selection
        }
    } else {
        // If there are no more questions, display "Quiz Completed" and show the final score
        questionLabel.setText("Quiz Completed!");
        for (JButton button : optionButtons) {
            button.setEnabled(false);  
        }
        scoreLabel.setText("Final Score: " + quiz.getScore() + "/" + quiz.getTotalQuestions());
    }
}



   private void checkAnswer(int answerIndex) {
    Question currentQuestion = quiz.getNextQuestion();
    if (currentQuestion != null && currentQuestion.getCorrectAnswerIndex() == answerIndex) {
        quiz.increaseScore();  // Increase score for correct answer
    }
    scoreLabel.setText("Score: " + quiz.getScore());  // Update score display
    showNextQuestion();  // Show the next question or the result if it's the last one
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizApp().setVisible(true);
            }
        });
    }
}
