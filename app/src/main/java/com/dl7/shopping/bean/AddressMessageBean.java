package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-17.
 */

public class AddressMessageBean {

    /**
     * event : 200
     * data : {"pageNum":1,"pageSize":10,"size":5,"orderBy":null,"startRow":1,"endRow":5,"total":5,"pages":1,"list":[{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","room_sort":2,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼2楼203房间","create_time":"2017-05-29 18:49:54","floor_num":2,"latitude":113.273289,"mobile":"17620005667","is_default":0,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"琳琳","is_del":"0","location":"AAAAAAEBAAAA4/xNKEQoN0DSViWRfVFcQA==","id":"8c716090-445c-11e7-8ace-b0d59d46982f","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":4,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼3层305房间","create_time":"2017-05-25 18:30:33","floor_num":3,"latitude":113.273289,"mobile":"18613088785","is_default":1,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"琪琪","is_del":"0","id":"2f2f2360-4135-11e7-a5eb-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":2,"address":"广东省广州市白云区广大女生宿舍楼北区2号楼3楼303房间","create_time":"2017-06-03 11:46:31","floor_num":3,"latitude":113.273289,"mobile":"18653398731","is_default":1,"community_id":"b9ce3250-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"苹果","is_del":"0","id":"3b461f10-480f-11e7-bbb9-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":4,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼2楼205房间","create_time":"2017-06-03 16:13:03","floor_num":2,"latitude":113.273289,"mobile":"12345678","is_default":1,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"测试","is_del":"0","id":"777fc150-4834-11e7-bbb9-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":3,"address":"广东省广州市白云区广大女生宿舍楼北区2号楼3楼304房间","create_time":"2017-06-13 15:06:35","floor_num":3,"latitude":113.273289,"mobile":"18613088788","is_default":1,"community_id":"b9ce3250-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"海绵宝宝","is_del":"0","id":"d63c6e80-5006-11e7-ad71-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     * message : 加载成功
     * timestamp : 2017-07-26 18:46:14
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
         * list : [{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","room_sort":2,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼2楼203房间","create_time":"2017-05-29 18:49:54","floor_num":2,"latitude":113.273289,"mobile":"17620005667","is_default":0,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"琳琳","is_del":"0","location":"AAAAAAEBAAAA4/xNKEQoN0DSViWRfVFcQA==","id":"8c716090-445c-11e7-8ace-b0d59d46982f","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":4,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼3层305房间","create_time":"2017-05-25 18:30:33","floor_num":3,"latitude":113.273289,"mobile":"18613088785","is_default":1,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"琪琪","is_del":"0","id":"2f2f2360-4135-11e7-a5eb-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":2,"address":"广东省广州市白云区广大女生宿舍楼北区2号楼3楼303房间","create_time":"2017-06-03 11:46:31","floor_num":3,"latitude":113.273289,"mobile":"18653398731","is_default":1,"community_id":"b9ce3250-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"苹果","is_del":"0","id":"3b461f10-480f-11e7-bbb9-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":4,"address":"广东省广州市白云区广大女生宿舍楼南区1号楼2楼205房间","create_time":"2017-06-03 16:13:03","floor_num":2,"latitude":113.273289,"mobile":"12345678","is_default":1,"community_id":"a7112370-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"测试","is_del":"0","id":"777fc150-4834-11e7-bbb9-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729},{"member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","store_id":"","room_sort":3,"address":"广东省广州市白云区广大女生宿舍楼北区2号楼3楼304房间","create_time":"2017-06-13 15:06:35","floor_num":3,"latitude":113.273289,"mobile":"18613088788","is_default":1,"community_id":"b9ce3250-3220-11e7-a002-b8975a6f72af","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","contact":"海绵宝宝","is_del":"0","id":"d63c6e80-5006-11e7-ad71-00163e062c7a","country_id":"c4ea5370-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","longitude":23.15729}]
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
             * member_id : 6d7e1950-4063-11e7-ad78-00163e062c7a
             * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
             * room_sort : 2
             * address : 广东省广州市白云区广大女生宿舍楼南区1号楼2楼203房间
             * create_time : 2017-05-29 18:49:54
             * floor_num : 2
             * latitude : 113.273289
             * mobile : 17620005667
             * is_default : 0
             * community_id : a7112370-3220-11e7-a002-b8975a6f72af
             * province_id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
             * contact : 琳琳
             * is_del : 0
             * location : AAAAAAEBAAAA4/xNKEQoN0DSViWRfVFcQA==
             * id : 8c716090-445c-11e7-8ace-b0d59d46982f
             * country_id : c4ea5370-0d2d-11e7-a212-b8975a6f72af
             * city_id : e01f3ba0-0d1f-11e7-aa69-b8975a6f72af
             * longitude : 23.15729
             */

            private String member_id;
            private String store_id;
            private int room_sort;
            private String address;
            private String create_time;
            private int floor_num;
            private double latitude;
            private String mobile;
            private int is_default;
            private String community_id;
            private String province_id;
            private String contact;
            private String is_del;
            private String location;
            private String id;
            private String country_id;
            private String city_id;
            private double longitude;

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
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

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
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

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }
}
