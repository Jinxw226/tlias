package com.itjf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzLog {
    private Integer id;
    private LocalDateTime operateTime;
    private String info;
}
