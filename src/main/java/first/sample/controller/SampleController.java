package first.sample.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView insertBoard(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap(), request);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		Map<String, Object> map = sampleService.selectBoardDetail(cm.getMap());
		/*
		 * System.out.println(cm.getMap()); System.out.println(map);
		 */
		mv.addObject("map", map);
		mv.addObject("list",map.get("list"));
		System.out.println(map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardUpdate");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map);
		
		return mv;
	}
	
	
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
		
		sampleService.updateBoard(cm.getMap());
		mv.addObject("IDX", cm.get("IDX"));		
		return mv;
	}
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap cm) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.deleteBoard(cm.getMap());	
		return mv;
	}
	
	
	
}
