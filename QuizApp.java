import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame {
    private Quiz quiz;
    private Question currentQuestion;
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
                    handleAnswer(index);
                }
            });
        }

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        showNextQuestion();
    }

    private void showNextQuestion() {
        currentQuestion = quiz.getNextQuestion();
        if (currentQuestion != null) {
            questionLabel.setText(currentQuestion.getQuestionText());
            String[] options = currentQuestion.getOptions();
            for (int i = 0; i < options.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setEnabled(true); // Enable all buttons for the next question
            }
        } else {
            questionLabel.setText("Quiz Completed!");
            for (JButton button : optionButtons) {
                button.setEnabled(false);
            }
            scoreLabel.setText("Final Score: " + quiz.getScore() + "/" + quiz.getTotalQuestions());
        }
    }

    private void handleAnswer(int answerIndex) {
        // Check if the selected answer is correct
        if (currentQuestion != null && currentQuestion.getCorrectAnswerIndex() == answerIndex) {
            quiz.increaseScore();
        }
        // Update the score display
        scoreLabel.setText("Score: " + quiz.getScore());
        // Proceed to the next question
        showNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().setVisible(true));
    }
}
