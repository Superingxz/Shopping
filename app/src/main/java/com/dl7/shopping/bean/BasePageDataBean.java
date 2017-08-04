package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by moligy on 2017/8/4.
 */

public class BasePageDataBean<T> {
    private int pageNum;
    private int pageSize;
    private int size;
    private Object orderBy;
    private int startRow;
    private int endRow;
    private int total;
    private int pages;
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private List<T> list;
    private List<Integer> navigatepageNums;
}
