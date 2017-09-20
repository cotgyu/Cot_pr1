package net.cot_pr1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cot_pr1.dao.AdminDao;
import net.cot_pr1.domain.WebBoard;
import net.cot_pr1.domain.WebReply;

@Service
public class AdminService {
	
	@Autowired
	AdminDao adminDao;

	public int countboard(String searchOption, String keyword) {
		return adminDao.countboard(searchOption, keyword);
	}

	public List<WebBoard> Viewlist(int start, int end, String searchOption, String keyword) {
		return adminDao.ViewList(start, end, searchOption, keyword);
	}
	
	public int countreply(String searchOption, String keyword) {
		return adminDao.countboard(searchOption, keyword);
	}

	public List<WebReply> Viewreplylist(int start, int end, String searchOption, String keyword) {
		return adminDao.Viewreplylist(start, end, searchOption, keyword);
	}

}
