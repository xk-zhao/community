package com.xk.zhao.community.entity;

/**
 * 封装分页的一些信息。
 */
public class Page {
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){
            this.current = current;
        }
    }

    public int getLimit() {

        return limit;
    }

    public void setLimit(int limit) {
        if (limit>=1&&limit<=100){
            this.limit = limit;
        }

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows>=0){
            this.rows = rows;
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //当前页码
    private int current = 1;
    //显示上线
    private int limit = 10;
    //数据总数，用于计算总页数
    private int rows;
    //查询路径
    //复用分页的链接
    private String path;

    /**
     * 获取当前页的起始行
     * @return
     */
    public int getOffset(){

        return (current-1)*limit;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotal(){
        if(rows%limit==0){
            return rows/limit;
        }else{
            return rows/limit + 1;
        }
    }

    /**
     * 获取当前页底部显示的起始页码
     * 如 第七页 应显示5 6 7 8 9
     * 本方法获得5
     * @return
     */
    public int getFrom(){
        int from = current - 2;
        return from<1?1:from;
    }

    /**
     * 获得当前页显示的结束页码
     * 如 第七页 应显示5 6 7 8 9
     * 本方法获得 9
     */
    public int getTo(){
        int to = current+2;
        int total = getTotal();
        return to>total?total:to;
    }
}
