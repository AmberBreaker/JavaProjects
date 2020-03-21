package com.shrm.mybatis.sqlsource;

import com.shrm.mybatis.mapping.ParameterMapping;
import com.shrm.mybatis.utils.TokenHandler;

import java.util.ArrayList;
import java.util.List;


public class ParameterMappingTokenHandler implements TokenHandler {
	private List<ParameterMapping> parameterMappings = new ArrayList<>();

	// content是参数名称，就是#{}中的内容
	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private ParameterMapping buildParameterMapping(String content) {
		ParameterMapping parameterMapping = new ParameterMapping(content);
		return parameterMapping;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
