package images;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImageHandlerTest {

    @Test
    void shouldCreateImages() {
        ImageHandler.createImages();
        assertThat(ImageHandler.getHiddenIcon()).isNotNull();
        assertThat(ImageHandler.getBombIcon()).isNotNull();
        assertThat(ImageHandler.getFlagIcon()).isNotNull();
    }
}
