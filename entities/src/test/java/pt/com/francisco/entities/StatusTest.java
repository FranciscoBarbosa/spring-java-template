package pt.com.francisco.entities;

import pt.com.francisco.entities.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    void shouldHaveFinishedStatus(){
        Assertions.assertThat(Status.valueOf("FINISHED")).isEqualTo(Status.FINISHED);
    }

    @Test
    void shouldHaveNotStartedStatus(){
        Assertions.assertThat(Status.valueOf("NOT_STARTED")).isEqualTo(Status.NOT_STARTED);
    }

    @Test
    void shouldHaveDoingStatus(){
        Assertions.assertThat(Status.valueOf("DOING")).isEqualTo(Status.DOING);
    }

}
