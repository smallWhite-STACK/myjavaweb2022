function addCart(bookId) {
    //因为是加入购物车，所以我们需要一个购物车的controller
    /*
    cart.do可以找到controller
    operate=addCart代表controller的addCart方法
    bookId代表addCart的参数bookId
    */
    window.location.href="cart.do?operate=addCart&bookId="+bookId;
}