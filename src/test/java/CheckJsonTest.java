import com.fasterxml.jackson.databind.ObjectMapper;
import model.MovieData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckJsonTest {
    private ClassLoader cl = CheckJsonTest.class.getClassLoader();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Tag("JSON_TEST")
    @DisplayName("Проверка json файла")
    void shouldCheckJsonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("movie.json");
             Reader reader = new InputStreamReader(is)) {
            MovieData data = objectMapper.readValue(is, MovieData.class);
            assertThat(data.getName()).isEqualTo("The Lord of the Rings: The Fellowship of the Ring");
            assertThat(data.getYear()).isEqualTo(2001);
            assertThat(data.getGenre()).isEqualTo("fantasy");
            assertThat(data.getCrew().getDirector()).isEqualTo("Peter Jackson");
            assertThat(data.getCrew().getMusic()).isEqualTo("Howard Shore");
            assertThat(data.getCrew().getLeadRole()).isEqualTo("Elijah Wood");
        }
    }
}

