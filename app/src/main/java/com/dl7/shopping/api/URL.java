package com.dl7.shopping.api;

/**
 * Created by MC.Zeng on 2017-07-13.
 */

public class URL {

    private static String prefix="http://192.168.1.77:17317";//请求前缀

    /**
     * 发送验证码
     * @param account
     * @param type="REGEDIT"
     * @return
     */
    public static String CODE_URL = prefix+"/sms/api/1.0/send";


    /**
     * 注册
     * @param account
     * @param passwd
     * @param code
     * @return
     */
    public static String REGISTER_URL = prefix+"/member/api/1.0/register";

    /**
     * 登录
     * @param account
     * @param passwd
     * @return
     */
    public static String LOGIN_URL = prefix+"/member/api/1.0/login";

    /**
     * 我的页面
     * @param member_id
     * @return
     */
    public static String MINE_URL = prefix+"/member/api/1.0/my/load";

    /**
     * 余额
     * @param member_id
     * @return
     */
    public static String BALANCE_URL = prefix+"/member/api/1.0/balance/load";

    /**
     * 爱心数据
     * @param member_id
     * @return
     */
    public static String LOVEDATA_URL = prefix+"/member/api/1.0/score/load";

    /**
     * 爱心明细
     * @param member_id
     * @return
     */
    public static String LOVEDETAIL_URL = prefix+"/member/api/1.0/score/detail/load";


    /**
     * 找回密码
     * @param account
     * @param code
     * @param passwd
     * @return
     */
    public static String FINDPASSWORD_URL = prefix+"/member/api/1.0/updatePwd";


    /**
     * 修改基本信息(传id和需要修改的参数，其他不用传)
     * @param id
     * @param name(昵称)、image(头像)、sex(性别)、idcard(身份证)
     * @return
     */
    public static String UPDATE_URL = prefix+"/member/api/1.0/customer/updateInfo";

    /**
     * 余额明细
     * @param member_id
     * @param pageNum
     * @return
     */
    public static String BALANCRDETAIL_URL = prefix+"/member/api/1.0/balance/detail/load";

    /**
     * 收货地址管理
     * @param member_id
     * @return
     */
    public static String ADDRESSMESSAGE_URL = prefix+"/address/api/1.0/load";

    /**
     * 设为默认地址
     * @param member_id
     * @param id
     * @return
     */
    public static String ADDRESSDEFULT_URL = prefix+"/address/api/1.0/default/set";

    /**
     * 删除地址
     * @param member_id
     * @param id
     * @return
     */
    public static String ADDRESSDELETE_URL = prefix+"/address/api/1.0/remove";

    /**
     * 选择地址(省市区)--获取省的不需要传参数，获取市的传省的参数，获取区的传市的参数
     * @param province_id
     * @param city_id
     * @return
     */
    public static String PROCITYCOUNTRY_URL = prefix+"/system/api/1.0/load/proCityCountry";

    /**
     * 订水页面
     * @param  store_id(每个地址都有一个绑定的水店,此为水店id)
     * @return
     */
    public static String WATERFRAGMENT_URL = prefix+"/water/api/1.0/goods/load";

    /**
     * 商品详情
     * @param goods_id
     * @return
     */
    public static String GOODSDETAIL_URL = prefix+"/water/api/1.0/goods/detail/load";

    /**
     * 用户取水
     * @param   member_id
     * @return
     */
    public static String GETWATER_URL = prefix+"/member/api/1.0/member/water/load";

    /**
     * 用水明细
     * @param   member_id
     * @param   pageNum
     * @return
     */
    public static String WATERDETAIL_URL = prefix+"/member/api/1.0/water/detail/load";

    /**
     * 读取默认地址
     * @param   member_id
     * @return
     */
    public static String DEFAULTADDRESS_URL = prefix+"/address/api/1.0/default/load";

    /**
     * 分类查询用户的余水
     * @param   member_id
     * @return
     */
    public static String BRANDWATER_URL = prefix+"/member/api/1.0/member/water/kind/brand";

    /**
     * 订单支付信息
     * @param   goods_id
     * @param   address_id
     * @param   address_id
     * @param   member_id
     * @return
     */
    public static String WATERORDER_URL = prefix+"/water/api/1.0/order/payment";

    /**
     * 附近水店
     * @param   longitude经度
     * @param   latitude纬度
     * @param   pageNum
     * @return
     */
    public static String WATERSTORE_URL = prefix+"/water/api/1.0/distance/load";


    /**
     * 修改水店
     * @param   id地址编号
     * @param   store_id水店ID
     * @return
     */
    public static String UPDATESTORE_URL = prefix+"/member/api/1.0/updateStore";

    /**
     * 支付
     * @param   business_type=“BUY_WATER”      商品类型
     * @param   company_id                     公司编号
     * @param   address_id                     地址编号
     * @param   goods_id                       商品编号
     * @param   amount(余水支付金额为0)         金额
     * @param   quantity                       数量
     * @param   resever                        预约时间
     * @param   sort                           预约排序
     * @param   subject                        水店名称
     * @param   channel(余水支付渠道为water)    支付渠道
     * @param   body                           商品备注(余水支付为[水店名称]+商品名称+"x"+数量)
     * @param   member_id
     * @return
     */
    public static String WATERPLAY_URL = prefix+"/water/order/api/1.0/create";


    /**
     * 学校配送时间
     * @return
     */
    public static String SCHOOLTIME_URL = prefix+"/water/store/api/1.0/resever/school/load";
}

