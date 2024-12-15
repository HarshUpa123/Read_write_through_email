package com.email_access.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailEntity {

    String to;
    String subject;
    String body;

}
