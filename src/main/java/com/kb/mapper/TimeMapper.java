package com.kb.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select(" SELECT now() ")
	public String getTime();
	
	public String getTime2();
}
