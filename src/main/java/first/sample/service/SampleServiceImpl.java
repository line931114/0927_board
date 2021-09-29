package first.sample.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import first.common.util.FileUtils;
import first.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sampleDAO.selectBoardList(map);
	}


	public void insertBoard(Map<String, Object> map,HttpServletRequest request ) throws Exception {
		// TODO Auto-generated method stub
		sampleDAO.insertBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++) {
			sampleDAO.insertFile(list.get(i));
		}
		
	}


	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sampleDAO.updateHitCnt(map);
		Map<String, Object> resultMap = sampleDAO.selectBoardDetail(map);
		resultMap.put("map", resultMap);
		
		List<Map<String,Object>> list = sampleDAO.selectFileList(map);
		resultMap.put("list", list);
		return resultMap;
		
	}

	public void updateBoard(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sampleDAO.updateBoard(map);
		
	}

	public void deleteBoard(Map<String, Object> map) throws Exception {
		sampleDAO.deleteBoard(map);
	}

}
