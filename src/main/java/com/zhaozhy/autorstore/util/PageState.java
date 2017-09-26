package com.zhaozhy.autorstore.util;

import javax.servlet.http.HttpServletRequest;
public class PageState
{

    String pages;
    String pageSize;

    public PageState(HttpServletRequest request)
    {
        pages = request.getParameter("pages");
        pageSize = request.getParameter("pagesize");
    }

    public String getPages()
    {
        return pages;
    }

    public void setPages(String pages)
    {
        this.pages = pages;
    }

    public String getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }
}
