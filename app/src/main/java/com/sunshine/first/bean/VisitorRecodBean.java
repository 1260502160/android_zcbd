package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class VisitorRecodBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message :
     * data : {"total":25,"totalPage":3,"list":[{"id":25,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"王田田","visi_mobile":"15201282084","start_time":"2019-10-16 00:00:00","end_time":"2019-10-16 23:59:59","car_num":"京C-839252","visi_imgs":"http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png","houses_number_name":"129","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"},{"id":24,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"王田田","visi_mobile":"15201282084","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"京C-839252","visi_imgs":"http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png","houses_number_name":"129","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"},{"id":23,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"王田田","visi_mobile":"15201282084","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"京C-839252","visi_imgs":"http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png","houses_number_name":"129","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"},{"id":22,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224php.jf.com/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":21,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224php.jf.com/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":20,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":19,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":18,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":17,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":16,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"}]}
     */

    private boolean success;
    private String error_code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 25
         * totalPage : 3
         * list : [{"id":25,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"王田田","visi_mobile":"15201282084","start_time":"2019-10-16 00:00:00","end_time":"2019-10-16 23:59:59","car_num":"京C-839252","visi_imgs":"http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png","houses_number_name":"129","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"},{"id":24,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"王田田","visi_mobile":"15201282084","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"京C-839252","visi_imgs":"http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png","houses_number_name":"129","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"},{"id":23,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"王田田","visi_mobile":"15201282084","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"京C-839252","visi_imgs":"http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png","houses_number_name":"129","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"},{"id":22,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224php.jf.com/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":21,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-10 00:00:00","end_time":"2019-10-10 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224php.jf.com/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":20,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":19,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":18,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":17,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"},{"id":16,"residents_id":18,"residents_name":"赵六","residents_mobile":"15232116969","visi_name":"访客姓名","visi_mobile":"15652591803","start_time":"2019-10-09 00:00:00","end_time":"2019-10-09 23:59:59","car_num":"车牌号","visi_imgs":"http://47.93.50.224/storage/xier/db5aa657c40d503ccd06ef9bfde635a8452.jpg","houses_number_name":"129","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇"}]
         */

        private int total;
        private int totalPage;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 25
             * residents_id : 18
             * residents_name : 赵六
             * residents_mobile : 15232116969
             * visi_name : 王田田
             * visi_mobile : 15201282084
             * start_time : 2019-10-16 00:00:00
             * end_time : 2019-10-16 23:59:59
             * car_num : 京C-839252
             * visi_imgs : http://47.93.50.224/storage/xier/e28b8bfe8acdd68e1a0338b869895c001393.png
             * houses_number_name : 129
             * floors_name : 1层
             * unitdoor_name : 5单元
             * building_name : 5号楼
             * community_name : 东1区
             */

            private int id;
            private int residents_id;
            private String residents_name;
            private String residents_mobile;
            private String visi_name;
            private String visi_mobile;
            private String start_time;
            private String end_time;
            private String car_num;
            private String visi_imgs;
            private String houses_number_name;
            private String floors_name;
            private String unitdoor_name;
            private String building_name;
            private String community_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getResidents_id() {
                return residents_id;
            }

            public void setResidents_id(int residents_id) {
                this.residents_id = residents_id;
            }

            public String getResidents_name() {
                return residents_name;
            }

            public void setResidents_name(String residents_name) {
                this.residents_name = residents_name;
            }

            public String getResidents_mobile() {
                return residents_mobile;
            }

            public void setResidents_mobile(String residents_mobile) {
                this.residents_mobile = residents_mobile;
            }

            public String getVisi_name() {
                return visi_name;
            }

            public void setVisi_name(String visi_name) {
                this.visi_name = visi_name;
            }

            public String getVisi_mobile() {
                return visi_mobile;
            }

            public void setVisi_mobile(String visi_mobile) {
                this.visi_mobile = visi_mobile;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getCar_num() {
                return car_num;
            }

            public void setCar_num(String car_num) {
                this.car_num = car_num;
            }

            public String getVisi_imgs() {
                return visi_imgs;
            }

            public void setVisi_imgs(String visi_imgs) {
                this.visi_imgs = visi_imgs;
            }

            public String getHouses_number_name() {
                return houses_number_name;
            }

            public void setHouses_number_name(String houses_number_name) {
                this.houses_number_name = houses_number_name;
            }

            public String getFloors_name() {
                return floors_name;
            }

            public void setFloors_name(String floors_name) {
                this.floors_name = floors_name;
            }

            public String getUnitdoor_name() {
                return unitdoor_name;
            }

            public void setUnitdoor_name(String unitdoor_name) {
                this.unitdoor_name = unitdoor_name;
            }

            public String getBuilding_name() {
                return building_name;
            }

            public void setBuilding_name(String building_name) {
                this.building_name = building_name;
            }

            public String getCommunity_name() {
                return community_name;
            }

            public void setCommunity_name(String community_name) {
                this.community_name = community_name;
            }
        }
    }
}
