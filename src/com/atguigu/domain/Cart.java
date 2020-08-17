package com.atguigu.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

// 购物车对象
public class Cart {

    /*
    // 购物车商品项
    * key是商品编号ID
    * value是商品信息
    */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /*
     * @Description : 购物车中添加商品项
     * @param cartItem 商品项对象
    */
    public void addItem(CartItem cartItem){
        // 直接add()，会在 Items直接添加商品，无法数量累加和金额更新
        // items.add(cartItem);

        // 先查看购物车是否已经添加过此商品，如果已添加，数量累加，如果未添加，则添加到 items中
        // 可以直接根据 Map集合的 get()方法，通过商品Id查询items是否包含该商品项
        CartItem item = items.get(cartItem.getId());

        if(item == null ){
            // 如果 item为空，不包含该商品项，则添加到 Items中
            items.put(cartItem.getId(),cartItem);
        }else {
            // 如果 item不为空，包含该商品项
            item.setCount( item.getCount()+1);  //数量累加
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal(item.getCount()))); //更新总金额
        }
    }

    /*
     * @Description : 根据商品项ID删除商品项
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /*
     * @Description : 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /*
     * @Description : 修改商品数量
     * @param id  商品项ID
     * @param count 商品项数量
     * @returns :
    */
    public void updateCount(Integer id,Integer count){
        // 先查看购物车是否已经有此商品，如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if(cartItem != null ){
            // cartItem不为空，证明已有此商品，设置商品的数量，更新商品总金额
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(count)));
        }
    }

    public Integer getTotalCount() {
        // 遍历累加商品项数目
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem>entry :items.entrySet()){
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        // 遍历累加商品总金额
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CartItem>entry :items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
