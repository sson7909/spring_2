package com.sson.util;

public class ListData {

	private int curPage;
	private int perPage;
	private String kind;
	private String search;
	
	public ListData() {
		curPage = 1;
		kind = "Title";
		search = "";
		this.perPage=10;
	}
	
	public RowNum makeRow(){
		RowNum rowNum = new RowNum();
		
		rowNum.setStartRow((curPage-1)*perPage +1);
		rowNum.setLastRow(curPage*perPage);
		rowNum.setSearch(search);
		rowNum.setKind(kind);
		
		return rowNum;
	}
	
	public Pager makePage(int totalCount){
		Pager pager = new Pager();
			int perBlock = 5;
			//1.totalPage 계산 
			int totalPage = 0;
			
			if(totalCount % perPage == 0){
				totalPage = totalCount/perPage;
			}else{
				totalPage =  totalCount/perPage+1;
			}
			
			//2.totalPage로 totalBlock 계산.
			int totalBlock = 0;
			
			if(totalPage%perBlock == 0){
				pager.setTotalBlock(totalPage/perBlock);
			}else{
				pager.setTotalBlock(totalPage/perBlock+1);
			}
			
			//3. curPage 로 curBlock 계산.
			int curBlock = 0;
			
			if(curPage%perBlock == 0){
				pager.setCurBlock(curPage/perBlock);
			}else{
				pager.setCurBlock(curPage/perBlock+1);
			}
			
			//4.curBlock 으로 startNum, lastNum 계산
			pager.setStartNum((pager.getCurBlock()-1)*perBlock+1);
			pager.setLastNum(pager.getCurBlock()*perBlock);
			
			
			//5.curBlock 이 마지막 Block 일때 lastNum
			if(pager.getCurBlock() == pager.getTotalBlock()){
				pager.setLastNum(totalPage);
			}
			pager.setSearch(search);
			pager.setKind(kind);
			
			
			
		return pager;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
}
