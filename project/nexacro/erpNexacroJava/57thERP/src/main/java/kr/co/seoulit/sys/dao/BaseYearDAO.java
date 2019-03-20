package kr.co.seoulit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.sys.to.BaseYearTO;
import kr.co.seoulit.sys.to.CodeTO;

public interface BaseYearDAO{

	public List<BaseYearTO> selectBaseYearList(Map<String,Object> vList);

}
