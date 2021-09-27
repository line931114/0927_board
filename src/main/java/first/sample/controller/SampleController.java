package first.sample.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.sample.service.SampleService;


@Controller
public class SampleController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value="/sample/openBoardList.do")
	public ModelAndView openBoardList(CommandMap cm) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardList");
		
		List<Map<String,Object>> list = sampleService.selectBoardList(cm.getMap());
		mv.addObject("list",list);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardWrite");	
		return mv;
	}
	
	
	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		Map<String, Object> map = sampleService.selectBoardDetail(cm.getMap());
		mv.addObject("map", map);
		
		
		return mv;
	}
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
		
		sampleService.updateBoard(cm.getMap());
		mv.addObject("idx", cm.get("idx"));		
		return mv;
	}
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.deleteBoard(cm.getMap());	
		return mv;
	}
	
	
	
}
