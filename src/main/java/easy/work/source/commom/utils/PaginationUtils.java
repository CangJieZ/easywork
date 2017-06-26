package easy.work.source.commom.utils;

/**
 * 
 * @author zhuwt   
 * @Description: 全局获取分页参数 pageStart pageSize
 * @ClassName: PaginationUtils.java   
 * @date 2017年5月12日 下午3:34:42
 */
public class PaginationUtils {
	// 定义两个threadLocal变量：pageNum和pageSize
	private static ThreadLocal<Integer> pageNum = new ThreadLocal<Integer>(); // 保存第几页
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>(); // 保存每页记录条数

	/*
	 * pageNum ：get、set、remove
	 */
	public static int getCurrPage() {
		Integer pn = pageNum.get();
		if (pn == null) {
			return 0;
		}
		return pn;
	}

	public static void setCurrPage(int pageNumValue) {
		pageNum.set(pageNumValue);
	}

	public static void removePageNum() {
		pageNum.remove();
	}

	/*
	 * pageSize ：get、set、remove
	 */
	public static int getPageSize() {
		Integer ps = pageSize.get();
		if (ps == null) {
			return 0;
		}
		return ps;
	}

	public static void setPageSize(int pageSizeValue) {
		pageSize.set(pageSizeValue);
	}

	public static void removePageSize() {
		pageSize.remove();
	}
}
