package easy.work.source.commom.base;

public class BaseQuery {
	
	/**
	 * 当前面码
	 */
	private int currPage = 1;

	/**
	 * 总页码
	 */
	private int totalNum = 0;
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	
	
	
	

}
