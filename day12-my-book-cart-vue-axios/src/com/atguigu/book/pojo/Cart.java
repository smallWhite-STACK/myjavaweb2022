package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @author xujian
 * @create 2022-12-31 14:14
 * Cart代表用户的购物车Cart中包含多个CartItem
 *
 * 属性：
 *  总计（总价钱）
 *  数量（购物车项的数量（不是书的数目））
 *  购物车项的map集合《Integet,CartItem》Integet是该购物车项中book对应的id
 *      其可以代表某书之前是否在购物车项中
 */
public class Cart {

    private Double totalPrice;
    private Integer totalCount;
    private Map<Integer,CartItem> cartItemMap;

    private Integer totalBookCount; //购物车中书本的总数量

    public Cart() {}


    public Double getTotalPrice() {
        totalPrice = 0.0;
        //需要遍历Cart中所有的CartItem
        if(cartItemMap!=null && cartItemMap.size()>0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer,CartItem> entry:entries){
                CartItem everyCartItem = entry.getValue();
                BigDecimal price1 = new BigDecimal(everyCartItem.getBook().getPrice()+"");
                BigDecimal buyCount = new BigDecimal(everyCartItem.getBuyCount()+"");
                BigDecimal multiply = price1.multiply(buyCount);
                double v = multiply.doubleValue();
                totalPrice+=v;
            }

        }
        return totalPrice;
    }


    public Integer getTotalCount() {
        totalCount = 0;
        //需要遍历Cart中所有的CartItem
        if(cartItemMap!=null && cartItemMap.size()>0){
            totalCount=cartItemMap.size();
        }
        return totalCount;
    }


    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Integer getTotalBookCount() {
//        totalBookCount=0;
//        //遍历cart中所有的cartItem
//        for(CartItem cartItem :  cartItemMap.values()){
//            totalBookCount+=cartItem.getBuyCount();
//        }


        //上述代码不足在于没有判断cartItemMap
        totalBookCount=0;
        //遍历cart中所有的cartItem
        if(cartItemMap!=null && cartItemMap.size()>0) {
            for (CartItem cartItem : cartItemMap.values()) {
                totalBookCount = totalBookCount+ cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
