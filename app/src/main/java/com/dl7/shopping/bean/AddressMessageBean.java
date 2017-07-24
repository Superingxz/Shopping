package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-17.
 */

public class AddressMessageBean {
    /**
     * event : 200
     * data : {"pageNum":1,"pageSize":10,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","room_sort":3,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼2楼204房间","create_time":"2017-06-24 16:46:31","floor_num":2,"mobile":"13823461820","is_default":0,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"小罗","is_del":"0","id":"9eaa6290-58b9-11e7-b1d8-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     * message : 加载成功
     * timestamp : 2017-07-17 09:43:34
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
         * size : 1
         * orderBy : null
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * list : [{"member_id":"7c9061f0-405e-11e7-a232-b0d59d46982f","room_sort":3,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼2楼204房间","create_time":"2017-06-24 16:46:31","floor_num":2,"mobile":"13823461820","is_default":0,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"小罗","is_del":"0","id":"9eaa6290-58b9-11e7-b1d8-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af"}]
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
             * room_sort : 3
             * address : 广东省广州市白云区广大女生宿舍楼南区1号楼2楼204房间
             * create_time : 2017-06-24 16:46:31
             * floor_num : 2
             * mobile : 13823461820
             * is_default : 0
             * community_id : a7112370-3220-11e7-a002-b8975a6f72af
             * province_id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
             * contact : 小罗
             * is_del : 0
             * id : 9eaa6290-58b9-11e7-b1d8-00163e062c7a
             * country_id : c4ea5370-0d2d-11e7-a212-b8975a6f72af
             * city_id : e01f3ba0-0d1f-11e7-aa69-b8975a6f72af
             */

            private String member_id;
            private int room_sort;
            private String address;
            private String create_time;
            private int floor_num;
            private String mobile;
            private int is_default;
            private String community_id;
            private String province_id;
            private String contact;
            private String is_del;
            private String id;
            private String country_id;
            private String city_id;

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public int getRoom_sort() {
                return room_sort;
            }

            public void setRoom_sort(int room_sort) {
                this.room_sort = room_sort;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getFloor_num() {
                return floor_num;
            }

            public void setFloor_num(int floor_num) {
                this.floor_num = floor_num;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getIs_default() {
                return is_default;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }

            public String getCommunity_id() {
                return community_id;
            }

            public void setCommunity_id(String community_id) {
                this.community_id = community_id;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }
        }
    }
}
