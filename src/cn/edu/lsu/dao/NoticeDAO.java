package cn.edu.lsu.dao;

import java.util.List;

import cn.edu.lsu.bean.Notice;




public interface NoticeDAO {
	//��̨ϵͳ����ѯ���еĹ���
		public List<Notice> getAllNotices();
		//��̨ϵͳ����ӹ���
		public int addNotice(Notice n);
		//��̨ϵͳ������id���ҹ���
		public Notice findNoticeById(String n_id);
		//��̨ϵͳ������id�޸ĵ�������
		public int updateNotice(Notice n);
		//��̨ϵͳ������idɾ������
		public int deleteNotice(String n_id);
		//ǰ̨ϵͳ����ѯ������ӻ��޸ĵ�һ������
		public Notice getRecentNotice();
}
