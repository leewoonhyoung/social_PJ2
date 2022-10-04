package ORG.STDCD.BOARD.controller;

import ORG.STDCD.BOARD.dto.BoardDTO;
import ORG.STDCD.BOARD.dto.PageRequestDTO;
import ORG.STDCD.BOARD.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO , Model model){

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes){

        log.info( "dto..." + boardDTO);

        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";

    }

//    @GetMapping("/read")
//    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model){
//        log.info("bno .." + bno);
//
//        BoardDTO boardDTO = boardService.get(bno);
//
//        log.info("boardDTO" + boardDTO);
//
//        model.addAttribute("dto", boardDTO);
//
//    }

    @GetMapping({ "/read", "/modify" })
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model){

        log.info("bno.. " + bno);

        BoardDTO boardDTO = boardService.get(bno);

        log.info("boardDTO : " + boardDTO);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        log.info("bno.." + bno);

        boardService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg,bno");

        return "redirect:/board/list";
    }
    @PostMapping("/modify")
    public  String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("post modify----------------");
        log.info("dto" + dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());

        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }


}
