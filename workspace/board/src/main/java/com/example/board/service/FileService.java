package com.example.board.service;

import com.example.board.domain.dto.FileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    List<FileDTO> getFileListByBoardId(Long boardId);

}
