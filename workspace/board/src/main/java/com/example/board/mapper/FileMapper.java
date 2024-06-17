package com.example.board.mapper;

import com.example.board.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    // 첨부파일 insert
    // 첨부파일은 게시글이 insert 될 때 날아가면 된다.
    void insertFile(FileVO fileVO);
}
