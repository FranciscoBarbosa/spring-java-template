package pt.com.francisco.frameworks.web;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidTaskRequest { // TODO: Is this good ?
    String invalidName;
    String invalidDescription;
    String invalidStatus;
}
