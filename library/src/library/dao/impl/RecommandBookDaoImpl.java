package library.dao.impl;

import org.springframework.stereotype.Repository;

import library.dao.RecommandBookDao;
import library.model.RecommandBook;

@Repository("recommandBookDao")
public class RecommandBookDaoImpl extends BaseDaoImpl<RecommandBook> implements RecommandBookDao{

}