package com.test.common.to;
import java.util.List;

public class ListForm {

		private int	endrow = 1; // �������� ȭ���� ����
		private int pagenum = 1; // ������������ȣ
		private int rowsize = 5; // ȭ�鿡 ������ �ٰ���
		private int endpage = 1; // ��������
		private int pagesize = 2; // ȭ�鿡 ������ ����������
		private int pagecount = 1; // ������������
		private int dbcount = 1; // �ѷ��ڵ尹��
		private List<?> list;

		
		public ListForm() {
			this.rowsize = rowsize;
			this.pagenum = pagenum;
			this.dbcount = dbcount;
		}

		public ListForm(int rowsize, int pagenum, int dbcount) {
			this.rowsize = rowsize;
			this.pagenum = pagenum;
			this.dbcount = dbcount;
		}
		
		private boolean previous;
		public void setRowsize(int rowsize) {
			this.rowsize = rowsize;
		}
		private boolean next;



		public void setPagenum(int pagenum){
			this.pagenum=pagenum;
		}

		public void setDbcount(int dbcount){
			this.dbcount=dbcount;
		}

		public int getPagenum(){
			return pagenum;
		}
		public int getStartrow(){
			return (getPagenum()-1)*getRowsize()+1;
		}
		public int getEndrow(){
			endrow= getStartrow()+getRowsize()-1;
			
			if(endrow>getDbcount())
				endrow = getDbcount();
			return endrow;
		}
		public int getRowsize(){
			return rowsize;
		}

		public int getDbcount(){
			return dbcount;
		}
		public int getStartpage(){
			return getPagenum()-((getPagenum()-1)%getPagesize());
		}
		public int getEndpage(){
			endpage= getStartpage()+getPagesize()-1;
			if(endpage>getPagecount())
				endpage = getPagecount();
			return endpage;
		}
		public int getPagesize(){
			return pagesize;
		}
		public int getPagecount(){
			pagecount=(getDbcount()-1)/getRowsize()+1;
			return pagecount;
		}
		public boolean isPrevious(){
			if(getStartpage()-getPagesize()>0)
				this.previous=true;
			else	this.previous=false;
			return this.previous;
		}
		public boolean isNext(){
			if(getStartpage()+getPagesize()<=getPagecount())
				this.next=true;
			else 	this.next=false;
			return this.next;
		}
		public void setList(List<?> list){
			this.list=list;
		}
		public List<?> getList(){
			return list;
		}
	}

