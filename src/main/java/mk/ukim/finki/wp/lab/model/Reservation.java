package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;
}
