package daos;

import datas.Highscore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HighscoreCSVFileDAOTest {

    @BeforeAll
    static void setUp() {

    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void getHighscoreList() {
        List<Highscore> expectedHighscores = new HighscoreCSVFileDAO().getHighscoreList(0);

        assertThat(expectedHighscores)
                .isNotNull()
                .hasSize(10);

    }
}
