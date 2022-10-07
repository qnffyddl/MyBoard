package com.example.board_0914.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
    private int fileNo;
    private int boardNoFk;
    private String fileName;
    private String filePath;
    private int fileMount;
    private String deleteYN;
}
