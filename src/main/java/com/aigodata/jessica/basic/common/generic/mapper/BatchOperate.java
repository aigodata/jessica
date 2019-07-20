package com.aigodata.jessica.basic.common.generic.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BatchOperate<T> {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	@Value("${mybatis.batchInsert.size}")
	private int batchInsertSize;

	public void batchOperate(List<T> list, BatchOperateUseGeneratedKeys<T> mapper) {
		int size = list.size();
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			for (int i = 0; i < size; i++) {
				mapper.pushQueue((T) list.get(i));
				if ((i > 0 && i % batchInsertSize == 0) || i == batchInsertSize - 1) {
					session.commit();
					session.clearCache();
				}
			}
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
	}
}
