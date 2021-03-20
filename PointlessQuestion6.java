/*
  @Author: AL-NOORNABI AL-QAUSAYEN AL-MAKKI
  @Date: 1/12/2020
  @Version: 1
  Suggestion 1: “Pointless” Answer Quiz program
  Program asks general knowledge questions to a group of people, and gives a score
  of 0 if all answers are correct, 0 if answers are wrong and other scores depending
  on how many answers are correct.
*/
import java.util.Scanner;

class PointlessQuestion6
{
  public static void main(String [] args)
  {
    final int NUMBER_OF_QUESTIONS = 4;

    String [] QuestionBank = new String [NUMBER_OF_QUESTIONS];
    initQuestionBank(QuestionBank);

    String [] CorrectAnswerBank = new String [NUMBER_OF_QUESTIONS];
    initCorrectAnswersBank(CorrectAnswerBank);

    final int NUMBER_OF_PLAYERS = inputInt("Enter number of people paricipating. ");

    QuizRecords [] q = new QuizRecords [NUMBER_OF_PLAYERS];
    initQuizArray(QuestionBank, CorrectAnswerBank, q );

    int [] playerScores = new int [NUMBER_OF_PLAYERS];
    initPlayerScores(playerScores, q);
    sortPlayerScores(playerScores);

    printPlayerScores(playerScores, q);

    System.exit(0);
  }
//*****************************************************************************
//Print leaderboard
  public static void printPlayerScores(int [] playerScores, QuizRecords [] q)
  {
    int key = 0;

    System.out.println("\nLEADERBOARD\n");

    for(int i=0; i<playerScores.length; i++)
    {
      key = findPlayerIndex(q, playerScores[i]);
      System.out.println("Player " + (key+1) + "\t" + playerScores[i]);
    }
  }

//*****************************************************************************
//List the players starting from lowest to highest scores
  public static int findPlayerIndex (QuizRecords [] q, int playerScores )
  {
    int position = 0;
    for(int i=0; i<q.length; i++)
    {
      for(int j=0; j<q.length; j++)
      {
        if(getScore(q[i])==playerScores)
        {
          position = i;
        }
      }
    }
    return position;
  }

//*****************************************************************************
//Rearrange the scores from a given to ascending order
  public static void sortPlayerScores(int [] playerScores)
  {
    int interim = 0;
    for(int i=0; i<playerScores.length; i++)
    {
      for(int j=0; j<playerScores.length;  j++)
      {
        if(playerScores[i]<playerScores[j])
        {
          interim = playerScores[i];
          playerScores[i] = playerScores[j];
          playerScores[j] = interim;
        }
      }
    }
  }

//*****************************************************************************
//Initialise playerScores with the scores of the players
  public static void initPlayerScores(int [] playerScores, QuizRecords [] q)
  {
    for(int i=0; i<playerScores.length; i++)
    {
      playerScores[i] = getScore(q[i]);
    }
  }

//*****************************************************************************
//Initialise QuizRecords q array record with aprropriate values
  public static void initQuizArray(String [] QuestionBank, String [] CorrectAnswerBank,
                                          QuizRecords [] q)
  {
    int person = 0;
    for(int i=0; i<q.length; i++ )
    {
      person = (i+1);
      System.out.println("\n" + "Player " + person);

      int score = 100;

      for(int j=0; j<QuestionBank.length; j++ )
      {
        String question = QuestionBank[j];

        String askQuestion = inputString(question);

        String correctAnswer = CorrectAnswerBank[j];

        boolean check = isCorrect(askQuestion, correctAnswer);


        int pointsIfCorrect = 25;
        int pointsIfIncorrect = 0;
        int pointsScored = calculateScore(check, pointsIfCorrect, pointsIfIncorrect);

        score = score - pointsScored;

        q[i] = initQuizRecords(question, correctAnswer, check, score);
        System.out.println(toString(askQuestion, score));
      }
      System.out.println("\n\n" + "Final score for Player " + person + " is " + getScore(q[i])
                         + "." + "\n\n");
    }
  }
//******************************************************************************
//Create an array of general knowledge questions
  public static void initQuestionBank(String [] QuestionBank)
  {
    QuestionBank[0] = "Which country did chicken tikka originate from?" +
                      "\n" + "Bangladesh" + "\n" + "India" + "\n" +
                      "United Kingdom" + "\n" + "\n";
    QuestionBank[1] = "How many months does it take a Pineapple to grow? " +
                      "\n" + "18-36 months" + "\n" + "7-12 months" + "\n" +
                      "3-4 months" + "\n" + "\n";
    QuestionBank[2] = "When was Nintendo founded? " +
                      "\n" + "1976" + "\n" + "1889" + "\n" +
                      "1971" + "\n" + "\n";
    QuestionBank[3] = "Which of these countries have the highest population? " +
                      "\n" + "Bangladesh" + "\n" + "Russia" + "\n" +
                      "United Kingdom" + "\n" + "\n";
  }

  public static void initCorrectAnswersBank(String [] CorrectAnswerBank)
  {
    CorrectAnswerBank[0] = "United Kingdom";
    CorrectAnswerBank[1] = "18-36 months";
    CorrectAnswerBank[2] = "1889";
    CorrectAnswerBank[3] = "Bangladesh";
  }

//******************************************************************************
//Initialise QuizRecords
  public static QuizRecords initQuizRecords(String question, String correctAnswer,
                                            boolean check, int score)
  {
    QuizRecords q = new QuizRecords();

    q = setQuestion(q, question);
    q = setCorrectAnswers(q, correctAnswer);
    q = setCheck(q, check);
    q = setScore(q, score);

    return q;
  }

//Setter methods for QuizRecords
  public static QuizRecords setQuestion(QuizRecords q, String question)
  {
    q.QuestionBank = question;
    return q;
  }
  public static QuizRecords setCorrectAnswers(QuizRecords q, String correctAnswer)
  {
    q.CorrectAnswerBank = correctAnswer;
    return q;
  }
  public static QuizRecords setCheck(QuizRecords q, boolean check)
  {
    q.Check = check;
    return q;
  }
  public static QuizRecords setScore(QuizRecords q, int score)
  {
    q.Score = score;
    return q;
  }
//End of setter methods

//Getter methods for initQuizRecords
  public static String getQuestion(QuizRecords q)
  {
    return q.QuestionBank;
  }
  public static String getCorrectAnswers(QuizRecords q)
  {
    return q.CorrectAnswerBank;
  }
  public static boolean getCheck(QuizRecords q)
  {
    return q.Check;
  }
  public static int getScore(QuizRecords q)
  {
    return q.Score;
  }
//End of Getter methods

//Convert various types to String
  public static String toString(String askQuestion, int score)
  {
    String convertedToString = "You answered " + askQuestion + "." + " Your score is " + score
                                + "\n";
    return convertedToString;
  }

//*****************************************************************************

//Calculate points scored
  public static int calculateScore(boolean check, int pointsIfCorrect, int pointsIfIncorrect)
  {
    if(check == true)
    {
      return pointsIfCorrect;
    }
    else
    {
      return pointsIfIncorrect;
    }
  }

//Check if answer is correct
  public static boolean isCorrect(String askQuestion, String correctAnswer)
  {
    return (askQuestion.equals(correctAnswer));
  }
//******************************************************************************

//General purpose method for inputting an integer
  public static String inputString(String message)
  {
    Scanner scanner = new Scanner(System.in);
    String answer;

    System.out.print(message);
    answer = scanner.nextLine();
    return answer;
  }

//General purpose method for inputting an integer
  public static int inputInt(String message)
  {
    String userInput = inputString(message);
    return Integer.parseInt(userInput);
  }
//******************************************************************************
}//End Class PointlessQuestion3

class QuizRecords
{
  String QuestionBank;
  String CorrectAnswerBank;
  boolean Check;
  int Score;
}
