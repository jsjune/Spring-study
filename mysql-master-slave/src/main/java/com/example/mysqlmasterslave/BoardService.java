package com.example.mysqlmasterslave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getAll() {
        return boardRepository.findAll();
    }
    
    public void save() {
        boardRepository.save(new Board("가나다"));
    }

    public void update(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        findBoard.get().setContents("나다라");
    }
}
