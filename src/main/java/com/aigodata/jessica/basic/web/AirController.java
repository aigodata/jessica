package com.aigodata.jessica.basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aigodata.jessica.basic.model.ResultModel;
import com.github.mengxianun.core.DataResultSet;
import com.github.mengxianun.core.Translator;

@RestController
public class AirController {

	@Autowired
	private Translator translator;

	@PostMapping("/action")
	public ResultModel action(@RequestBody String requestJson) {
		DataResultSet dataResultSet = translator.translate(requestJson);
		if (!dataResultSet.succeed()) {
			return ResultModel.failWithError(dataResultSet.getCode(), dataResultSet.getMessage());
		}
		return ResultModel.success(dataResultSet.getData());
	}

}
