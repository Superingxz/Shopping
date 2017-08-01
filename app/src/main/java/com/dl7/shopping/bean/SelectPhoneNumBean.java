package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-29.
 */

public class SelectPhoneNumBean {
    /**
     * event : 200
     * data : {"pageNum":1,"pageSize":10,"size":5,"orderBy":null,"startRow":1,"endRow":5,"total":5,"pages":1,"list":[{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"16","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011239","id":"a40a6ac0-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"17","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011240","id":"a41dcbb0-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"18","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011241","id":"a44b1d40-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"19","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011242","id":"a4650de0-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"20","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011243","id":"a499d980-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     * message : 加载成功
     * timestamp : 2017-07-29 17:56:43
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
         * size : 5
         * orderBy : null
         * startRow : 1
         * endRow : 5
         * total : 5
         * pages : 1
         * list : [{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"16","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011239","id":"a40a6ac0-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"17","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011240","id":"a41dcbb0-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"18","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011241","id":"a44b1d40-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"19","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011242","id":"a4650de0-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"},{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","create_user_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","card_number":"20","create_time":"2017-07-29 12:51:51","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","is_del":"0","goods_id":"","phone_number":"13102011243","id":"a499d980-7419-11e7-a591-00e066f08ac3","card_type":"UNICOM","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"WAITSALE"}]
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
             * store_id : c6590bb0-614d-11e7-b74a-bc5ff4fd8413
             * create_user_id : 7c9061f0-405e-11e7-a232-b0d59d46982f
             * card_number : 16
             * create_time : 2017-07-29 12:51:51
             * province_id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
             * is_del : 0
             * goods_id :
             * phone_number : 13102011239
             * id : a40a6ac0-7419-11e7-a591-00e066f08ac3
             * card_type : UNICOM
             * city_id : e01f3ba0-0d1f-11e7-aa69-b8975a6f72af
             * status : WAITSALE
             */

            private String store_id;
            private String create_user_id;
            private String card_number;
            private String create_time;
            private String province_id;
            private String is_del;
            private String goods_id;
            private String phone_number;
            private String id;
            private String card_type;
            private String city_id;
            private String status;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getCreate_user_id() {
                return create_user_id;
            }

            public void setCreate_user_id(String create_user_id) {
                this.create_user_id = create_user_id;
            }

            public String getCard_number() {
                return card_number;
            }

            public void setCard_number(String card_number) {
                this.card_number = card_number;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCard_type() {
                return card_type;
            }

            public void setCard_type(String card_type) {
                this.card_type = card_type;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
