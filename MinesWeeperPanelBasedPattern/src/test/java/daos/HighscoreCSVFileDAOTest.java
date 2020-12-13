package daos;

import datas.Highscore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HighscoreCSVFileDAOTest {

    @Test
    public void getHighscoreList() {
        List<Highscore> expectedHighscores = new HighscoreCSVFileDAO().getHighscoreList(0);

        assertThat(expectedHighscores).isNotNull();
        assertThat(expectedHighscores).hasSize(10);

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }
}