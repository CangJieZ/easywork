package easy.work.source.commom.base;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 
 * @author zhuwt
 * @Description: 分页查询 数据返回
 * @ClassName: PageMode.java
 * @date 2017年5月12日 下午2:32:46
 * @param <T>
 */
public class PageMode<T> implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8545828760250141949L;
	private long totalNum; // 总记录数
	private List<T> list; // 结果集
	private int currPage; // 第几页
	private int pageSize; // 每页记录数
	private int totalPages; // 总页数
	private int size; // 当前页的数量

	/**
	 *  包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理， 而出现一些问题。
	 * <p>Title: </p>   
	 * <p>Description: </p>   
	 * @param list
	 */
	public PageMode(List<T> list) {
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			this.currPage = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.totalNum = page.getTotal();
			this.totalPages = page.getPages();
			this.list = page;
			this.size = page.size();
		}
	}

	

	public long getTotalNum() {
		return totalNum;
	}



	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}



	public int getCurrPage() {
		return currPage;
	}



	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}



	public int getTotalPages() {
		return totalPages;
	}



	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}



	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
