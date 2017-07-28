package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-15.
 */

public class LoveListBean {

    /**
     * event : 200
     * data : {"pageNum":1,"pageSize":10,"size":7,"orderBy":null,"startRow":1,"endRow":7,"total":7,"pages":1,"list":[{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":3,"notes":"购买商品【水之星桶装水】获得爱心","type":"INCOME","before_score":6.65,"create_time":"2017-07-24 17:18:42","after_score":9.65,"id":"1d905c00-58be-11e7-b1d8-00163e062c7a","source":"BUYGOODS","type":"INCOME","order_id":"9c68cfa0-58bc-11e7-9dd5-00163e062c7a","mqkey":"1451189048"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":3,"notes":"购买商品【水之星桶装水】获得爱心","before_score":3.65,"create_time":"2017-07-24 17:04:03","after_score":6.65,"id":"11d272b0-58bc-11e7-b1d8-00163e062c7a","source":"BUYGOODS","type":"INCOME","order_id":"c2246a90-58b9-11e7-9dd5-00163e062c7a","mqkey":"3299665567"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":3,"notes":"购买商品【水之星桶装水】获得爱心","before_score":0.65,"create_time":"2017-07-24 17:04:02","after_score":3.65,"id":"11552c60-58bc-11e7-b1d8-00163e062c7a","source":"BUYGOODS","type":"INCOME","order_id":"c2246a90-58b9-11e7-9dd5-00163e062c7a","mqkey":"3299665567"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.05,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.6,"create_time":"2017-07-24 16:59:12","after_score":0.65,"id":"64428860-58bb-11e7-b1d8-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"8bbb9550-58b9-11e7-9dd5-00163e062c7a","mqkey":"2471333101"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.05,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.5,"create_time":"2017-07-24 16:42:27","after_score":0.55,"id":"0d3f5d60-58b9-11e7-a9d7-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"71acdb70-58b8-11e7-9dd5-00163e062c7a","mqkey":"1880513271"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.05,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.35,"create_time":"2017-07-24 14:08:05","after_score":0.4,"id":"7ca85690-58a3-11e7-a9d7-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"8893e6f0-58a2-11e7-9dd5-00163e062c7a","mqkey":"3469920891"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.1,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.05,"create_time":"2017-07-24 11:41:41","after_score":0.15,"id":"08fcead0-588f-11e7-a9d7-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"91b1d300-588e-11e7-9dd5-00163e062c7a","mqkey":"3711943592"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     * message : 加载成功
     * timestamp : 2017-07-15 11:22:45
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
         * size : 7
         * orderBy : null
         * startRow : 1
         * endRow : 7
         * total : 7
         * pages : 1
         * list : [{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":3,"notes":"购买商品【水之星桶装水】获得爱心","type":"INCOME","before_score":6.65,"create_time":"2017-07-24 17:18:42","after_score":9.65,"id":"1d905c00-58be-11e7-b1d8-00163e062c7a","source":"BUYGOODS","type":"INCOME","order_id":"9c68cfa0-58bc-11e7-9dd5-00163e062c7a","mqkey":"1451189048"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":3,"notes":"购买商品【水之星桶装水】获得爱心","before_score":3.65,"create_time":"2017-07-24 17:04:03","after_score":6.65,"id":"11d272b0-58bc-11e7-b1d8-00163e062c7a","source":"BUYGOODS","type":"INCOME","order_id":"c2246a90-58b9-11e7-9dd5-00163e062c7a","mqkey":"3299665567"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":3,"notes":"购买商品【水之星桶装水】获得爱心","before_score":0.65,"create_time":"2017-07-24 17:04:02","after_score":3.65,"id":"11552c60-58bc-11e7-b1d8-00163e062c7a","source":"BUYGOODS","type":"INCOME","order_id":"c2246a90-58b9-11e7-9dd5-00163e062c7a","mqkey":"3299665567"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.05,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.6,"create_time":"2017-07-24 16:59:12","after_score":0.65,"id":"64428860-58bb-11e7-b1d8-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"8bbb9550-58b9-11e7-9dd5-00163e062c7a","mqkey":"2471333101"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.05,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.5,"create_time":"2017-07-24 16:42:27","after_score":0.55,"id":"0d3f5d60-58b9-11e7-a9d7-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"71acdb70-58b8-11e7-9dd5-00163e062c7a","mqkey":"1880513271"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.05,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.35,"create_time":"2017-07-24 14:08:05","after_score":0.4,"id":"7ca85690-58a3-11e7-a9d7-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"8893e6f0-58a2-11e7-9dd5-00163e062c7a","mqkey":"3469920891"},{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","score":0.1,"notes":"一度使者【18613088785】消费激励爱心","before_score":0.05,"create_time":"2017-07-24 11:41:41","after_score":0.15,"id":"08fcead0-588f-11e7-a9d7-00163e062c7a","source":"ONESTEP","type":"INCOME","order_id":"91b1d300-588e-11e7-9dd5-00163e062c7a","mqkey":"3711943592"}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 0
         * lastPage : 1
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
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
             * member_id : 7c9061f0-405e-11e7-a232-b0d59d46982f
             * score : 3.0
             * notes : 购买商品【水之星桶装水】获得爱心
             * before_score : 6.65
             * type:INCOME
             * create_time : 2017-07-24 17:18:42
             * after_score : 9.65
             * id : 1d905c00-58be-11e7-b1d8-00163e062c7a
             * source : BUYGOODS

             * order_id : 9c68cfa0-58bc-11e7-9dd5-00163e062c7a
             * mqkey : 1451189048
             */

            private String member_id;
            private double score;
            private String notes;
            private double before_score;
            private String create_time;
            private double after_score;
            private String id;
            private String source;
            private String type;
            private String order_id;
            private String mqkey;

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public double getBefore_score() {
                return before_score;
            }

            public void setBefore_score(double before_score) {
                this.before_score = before_score;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public double getAfter_score() {
                return after_score;
            }

            public void setAfter_score(double after_score) {
                this.after_score = after_score;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getMqkey() {
                return mqkey;
            }

            public void setMqkey(String mqkey) {
                this.mqkey = mqkey;
            }
        }
    }
}
