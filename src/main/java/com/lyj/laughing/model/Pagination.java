package com.lyj.laughing.model;

import java.util.List;

public class Pagination
{
	/** 数据总量 */
	private int totalElements;
	/** 每页大小 */
	private int pageSize;
	/** 总页数 */
	private int totalPages;
	/** 页码 */
	private int pageIndex;
	/** 当前数据 */
	private List<Content> currData;
	/** 每页分页链接数量 */
	private int pageLinkNumber;
	/** 分页链接的开始页 */
	private int beginIndex;
	/** 分页链接的结束页 */
	private int endIndex;

	public Pagination(int totalElements, int pageIndex, int pageSize, List<Content> currData)
	{
		this.totalElements = totalElements;
		setPageSize(pageSize);
		setTotalPages();
		setPageIndex(pageIndex);
		// 默认分页链接多少个数据
		this.pageLinkNumber = 3;
		setBeginEndIndex();
		this.currData = currData;
	}

	public int getTotalElements()
	{
		return totalElements;
	}

	public void setTotalElements(int totalElements)
	{
		this.totalElements = totalElements;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		if (pageSize < 1)
		{
			this.pageSize = 1;
		}
		else if (pageSize > totalElements)
		{
			this.pageSize = totalElements;
		}
		else
		{
			this.pageSize = pageSize;
		}
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages()
	{
		if (pageSize > 0)
		{
			this.totalPages = (totalElements + pageSize - 1) / pageSize;
		}
	}

	public int getPageIndex()
	{
		return pageIndex;
	}

	public void setPageIndex(int pageIndex)
	{
		if (pageIndex < 1)
		{
			this.pageIndex = 1;
		}
		else if (pageIndex > totalPages)
		{
			this.pageIndex = totalPages;
		}
		else
		{
			this.pageIndex = pageIndex;
		}
	}

	public List<Content> getCurrData()
	{
		return currData;
	}

	public void setCurrData(List<Content> currData)
	{
		this.currData = currData;
	}

	public int getPageLinkNumber()
	{
		return pageLinkNumber;
	}

	public void setPageLinkNumber(int pageLinkNumber)
	{
		if (pageLinkNumber < 0)
		{
			this.pageLinkNumber = 0;
		}
		else if (pageLinkNumber > totalPages)
		{
			this.pageLinkNumber = totalPages;
		}
		else
		{
			this.pageLinkNumber = pageLinkNumber;
		}
	}

	/** 设置分页链接的开始和结束 */
	public void setBeginEndIndex()
	{
		// 是否是偶数
		boolean isOdd = pageLinkNumber % 2 == 0;
		int val = pageLinkNumber / 2;
		beginIndex = pageIndex - (isOdd ? val - 1 : val);
		endIndex = pageIndex + val;
		if (beginIndex < 1)
		{
			beginIndex = 1;
			endIndex = pageLinkNumber;
		}
		if (endIndex > totalPages)
		{
			endIndex = totalPages;
			beginIndex = endIndex - pageLinkNumber + 1;
		}
	}

	public int getBeginIndex()
	{
		return beginIndex;
	}

	public int getEndIndex()
	{
		return endIndex;
	}

	public boolean isFirst()
	{
		return pageIndex == 1;
	}

	public boolean isLast()
	{
		return pageIndex == totalPages;
	}

	public boolean isPrevious()
	{
		return pageIndex > 1;
	}

	public boolean isNext()
	{
		return pageIndex < totalPages;
	}

	public int getPreviousIndex()
	{
		return isPrevious() ? pageIndex - 1 : 1;
	}

	public int getNextIndex()
	{
		return isNext() ? pageIndex + 1 : totalPages;
	}

	@Override
	public String toString()
	{
		return "Pagination [totalElements=" + totalElements + ", pageSize=" + pageSize + ", totalPages=" + totalPages
				+ ", pageIndex=" + pageIndex + ", currData=" + currData + ", pageLinkNumber=" + pageLinkNumber + "]";
	}

}
