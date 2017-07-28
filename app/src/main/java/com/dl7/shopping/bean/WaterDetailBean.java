package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-22.
 */

public class WaterDetailBean {

    /**
     * event : 200
     * data : {"pageNum":1,"pageSize":10,"size":10,"orderBy":null,"startRow":1,"endRow":10,"total":30,"pages":3,"list":[{"number":1,"notes":"消费 农夫山泉桶装水 1 桶","create_time":"2017-06-22 13:52:41","id":"012d9560-570f-11e7-8de6-00163e062c7a","type":"SPENDING"},{"number":20,"notes":"购买 怡宝桶装水 20 桶,已分配20 桶,待分配0 桶","create_time":"2017-07-22 09:35:12","id":"01744060-6e7e-11e7-8b3c-0036766903e9","type":"INCOME"},{"number":1,"notes":"消费 农夫山泉桶装水 1 桶","create_time":"2017-06-24 16:42:10","id":"0343d2a0-58b9-11e7-a9d7-00163e062c7a","type":"SPENDING"},{"number":20,"notes":"购买 农夫山泉桶装水 20 桶","create_time":"2017-06-16 14:43:11","id":"11091c70-525f-11e7-b4f7-00163e062c7a","type":"INCOME"},{"number":10,"notes":"分配 农夫山泉桶装水 10 桶,待分配水量为60 桶","create_time":"2017-07-13 17:03:00","id":"12342d00-67aa-11e7-be54-b8975a6f72af","type":"ALLOT"},{"number":10,"notes":"分配 农夫山泉桶装水 10 桶,待分配水量为50 桶","create_time":"2017-07-13 17:04:00","id":"35c0d2a0-67aa-11e7-be54-b8975a6f72af","type":"ALLOT"},{"number":2,"notes":"消费 农夫山泉桶装水 2 桶","create_time":"2017-06-16 14:44:17","id":"3806ccf0-525f-11e7-b4f7-00163e062c7a","type":"SPENDING"},{"number":20,"notes":"购买 农夫山泉桶装水 20 桶,已分配20 桶,待分配0 桶","create_time":"2017-07-22 09:36:51","id":"3c5ecd30-6e7e-11e7-9f46-00e066f08ac3","type":"INCOME"},{"number":12,"notes":"购买 农夫山泉20桶 12 桶,已分配2 桶,待分配10 桶","create_time":"2017-07-13 17:47:12","id":"3eb84c20-67b0-11e7-87c0-b8975a6f72af","type":"INCOME"},{"number":20,"notes":"购买 农夫山泉20桶 20 桶,已分配2 桶,待分配18 桶","create_time":"2017-07-13 17:40:06","id":"40c54fa0-67af-11e7-bc8a-b8975a6f72af","type":"INCOME"}],"firstPage":1,"prePage":0,"nextPage":2,"lastPage":3,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3]}
     * message : 加载成功
     * timestamp : 2017-07-22 11:15:59
     */

    private String event;
    private DataBean data;
    private String message;
    private String timestamp;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * orderBy : null
         * startRow : 1
         * endRow : 10
         * total : 30
         * pages : 3
         * list : [{"number":1,"notes":"消费 农夫山泉桶装水 1 桶","create_time":"2017-06-22 13:52:41","id":"012d9560-570f-11e7-8de6-00163e062c7a","type":"SPENDING"},{"number":20,"notes":"购买 怡宝桶装水 20 桶,已分配20 桶,待分配0 桶","create_time":"2017-07-22 09:35:12","id":"01744060-6e7e-11e7-8b3c-0036766903e9","type":"INCOME"},{"number":1,"notes":"消费 农夫山泉桶装水 1 桶","create_time":"2017-06-24 16:42:10","id":"0343d2a0-58b9-11e7-a9d7-00163e062c7a","type":"SPENDING"},{"number":20,"notes":"购买 农夫山泉桶装水 20 桶","create_time":"2017-06-16 14:43:11","id":"11091c70-525f-11e7-b4f7-00163e062c7a","type":"INCOME"},{"number":10,"notes":"分配 农夫山泉桶装水 10 桶,待分配水量为60 桶","create_time":"2017-07-13 17:03:00","id":"12342d00-67aa-11e7-be54-b8975a6f72af","type":"ALLOT"},{"number":10,"notes":"分配 农夫山泉桶装水 10 桶,待分配水量为50 桶","create_time":"2017-07-13 17:04:00","id":"35c0d2a0-67aa-11e7-be54-b8975a6f72af","type":"ALLOT"},{"number":2,"notes":"消费 农夫山泉桶装水 2 桶","create_time":"2017-06-16 14:44:17","id":"3806ccf0-525f-11e7-b4f7-00163e062c7a","type":"SPENDING"},{"number":20,"notes":"购买 农夫山泉桶装水 20 桶,已分配20 桶,待分配0 桶","create_time":"2017-07-22 09:36:51","id":"3c5ecd30-6e7e-11e7-9f46-00e066f08ac3","type":"INCOME"},{"number":12,"notes":"购买 农夫山泉20桶 12 桶,已分配2 桶,待分配10 桶","create_time":"2017-07-13 17:47:12","id":"3eb84c20-67b0-11e7-87c0-b8975a6f72af","type":"INCOME"},{"number":20,"notes":"购买 农夫山泉20桶 20 桶,已分配2 桶,待分配18 桶","create_time":"2017-07-13 17:40:06","id":"40c54fa0-67af-11e7-bc8a-b8975a6f72af","type":"INCOME"}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 2
         * lastPage : 3
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3]
         */

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
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
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

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * number : 1
             * notes : 消费 农夫山泉桶装水 1 桶
             * create_time : 2017-06-22 13:52:41
             * id : 012d9560-570f-11e7-8de6-00163e062c7a
             * type : SPENDING
             */

            private int number;
            private String notes;
            private String create_time;
            private String id;
            private String type;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
