package cn.haoyunfeng.concurrent.completablefuture;

/**
 * @Description:
 * @author: haoyunfeng2
 * @Date: 2019/10/9 5:21 下午
 */
public class ProductService {
    public static Product getProductDetail(String productId){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("name"+productId);
        return product;
    }
}
