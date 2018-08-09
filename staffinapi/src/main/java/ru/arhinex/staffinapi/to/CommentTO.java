package ru.arhinex.staffinapi.to;

import lombok.Getter;
import lombok.Setter;
import ru.arhinex.baseapi.to.DateCreatedTO;

@Setter @Getter
public class CommentTO extends DateCreatedTO {
    private String comment;
}
