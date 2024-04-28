package online.agatstudio.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer {

    private Long id;
    private String fio;
    private String phone;
    private String address;
    private LocalDateTime created;
}
