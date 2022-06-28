package ru.otus.spring.hw01.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.services.impl.QuestionsParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Questions parser")
class QuestionsParserTest {

    @Test
    @DisplayName("should correctly parse lines to questions")
    void shouldCorrectlyParseLinesToQuestions() {
        QuestionsParser questionsParser = new QuestionsParser(",", ";");

        List<Question> questions = questionsParser.parse(getQuestionLines());

        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }

    private List<String> getQuestionLines() {
        return List.of(
                "How many OOP principles exist?,one;three;five;seven;nine",
                "Is it possible to have multiple inheritance in Java?,no;yes",
                "What design pattern name is correct?,Designer;Element;Adapter",
                "How many primitive types java has?,five;four;eight;seven",
                "Which class do all classes in Java extend?,Proxy;Object;Class;Entity"
        );
    }

    private List<Question> getQuestions() {
        return List.of(
                new Question("How many OOP principles exist?", List.of("one", "three", "five", "seven", "nine")),
                new Question("Is it possible to have multiple inheritance in Java?", List.of("no", "yes")),
                new Question("What design pattern name is correct?", List.of("Designer", "Element", "Adapter")),
                new Question("How many primitive types java has?", List.of("five", "four", "eight", "seven")),
                new Question("Which class do all classes in Java extend?", List.of("Proxy", "Object", "Class", "Entity"))
        );
    }
}
