package com.example.mysqlmasterslave;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public List<Board> getAll() {
        return boardService.getAll();
    }

    @PostMapping("/boards/save")
    public void save() {
        boardService.save();
    }

    @PutMapping("/boards/update/{boardId}")
    public void update(@PathVariable Long boardId){
        boardService.update(boardId);
    }
}
