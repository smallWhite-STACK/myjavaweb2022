在之前的cart.html中（我们是通过index.html的“购物车”按钮（/cart.do）跳转的），
然后我们跳转到cartController的index方法
            public String index(HttpSession session){
                    User currUser = (User) session.getAttribute("currUser");
                    Cart cart = cartItemService.getCart(currUser);
                    currUser.setCart(cart);
                    session.setAttribute("currUser", currUser);
                    //然后去修改cart.html
                    return "cart/cart"; //是为了thymeleaf渲染
                }


现在的方式（VUE+AXIOS）：
    浏览器向服务器的cart.html发送请求，然后服务器响应
        服务器：
                1.ajax请求，请求购物车数据
                2.通过VUE在页面上展示购物车数据

    具体步骤：
        - 1.修改index.html的“购物车超链接”
            原始：<a th:href="@{/cart.do}" class="cart iconfont icon-gouwuche">
            现在：
                方式1： <a th:href="@{/page.do(operate='page',page='cart/cart')}" class="cart iconfont icon-gouwuche">
                方式2：
        - 2.在cart.html 展示购物车数据的外侧div标签--》<div class="list" >中加入一个id="cart_div"
            该id的作用就是将vue与div绑定，代表在改标签内部，vue是有效的
        - 3.在cart.js中加入window.onload事件
                (记得在cart.html中引入vue的js文件)
        - 4.在cartController中写一个getCartInfo方法代表以前的index方法

        - 5.运行，我们需要观察三个点
              1.首先客户端服务器发送请求（看看服务器可以获取curr用户）
              2.服务器将curr用户转换为json
              3.服务器将json发给客户端，客户端观察获取的json对象的data值
              （我们发现data相当于用户的购物车，我们将这个属性值定义为var cart）
              4.由于vue是可以把接收的数据绑定到页面上去，因此我们可以将cart写到vue的data中去，
                实现获取服务器的响应并且更新页面
                注意：尽管我们咋vue对象的data中写上"cart:{}",虽然为空，但是其代表vue有cart这个属性，不能不写。
        - 6.我们在vue中更新了cart属性，我们此时就可以在html页面上使用得到的cart来更新
            - 原始（我们将cartItemMap设置为currUser的属性，以此遍历）：
                            <tr th:each="cartItem : ${session.currUser.cart.cartItemMap.values()}">
                              <td>
                                <img th:src="@{|/static/uploads/${cartItem.book.bookImg}|}" />
                              </td>
                              <td th:text="${cartItem.book.bookName}">活着</td>
                              <td>
                                <span class="count" th:onclick="|editCart(${cartItem.id},${cartItem.buyCount-1})|">-</span>
                                <!--6.4 th:value不是th:text-->
                                <input class="count-num" type="text" value="1" th:value="${cartItem.buyCount}"/>
                                <span class="count" th:onclick="|editCart(${cartItem.id},${cartItem.buyCount+1})|">+</span>
                              </td>
                              <td th:text="${cartItem.book.price}">36.8</td>
                              <td th:text="${cartItem.xj}">36.8</td>
                              <td><a href="">删除12</a></td>
                            </tr>
            - 现在（vue+axios）：
                        <tr v-for="cartItem in cart.cartItemMap">
                            <td>
                              <img v-bind:src="'static/uploads/'+cartItem.book.bookImg" />
                            </td>
                            <td>{{cartItem.book.bookName}}</td>
                            <td>
                              <span class="count" @click="editCart(cartItem.id,cartItem.buyCount-1)">-</span>
                              <input class="count-num" type="text" value="1" v-bind:value="cartItem.buyCount"/>
                              <span class="count" @click="editCart(cartItem.id,cartItem.buyCount+1)">+</span>
                            </td>
                            <td>{{cartItem.book.price}}</td>
                            <td>{{cartItem.xj}}</td>
                            <td><a href="">删除12</a></td>
                          </tr>
            - 遇见的问题：
                         1.CartItem都没有xj（因为客户端收到的xj为null，其自动把其扔掉）
                         解决方法：在CartItemServiceImpl的getCartItemList(User user)中调用getXj
                         2.书名乱码问题：
                         解决方法：可以在filter中设置response的编码或者在dispatcherServlet中的json部分设置
                         3.“+”和“-”按钮失效，
                         分析：因为原始的editCart是一个同步方法，我们现在是利用vue实现异步
                         解决方法：
                                1. 因此需将editCart写在vue的methods内部，并且修改函数体；
                                2. 然后再controller中修改
                                原始editCart：
                                    function editCart(cartItemId,buyCount){
                                         window.location.href='cart.do?operate=editCart&cartItemId='+cartItemId+'&buyCount='+buyCount;
                                         }
                                 修改后：
                                        editCart:function(cartItemId,buyCount){
                                                    axios({
                                                        method: "POST",
                                                        url: "cart.do",
                                                        // data:{
                                                        //
                                                        // },
                                                        params: {
                                                            //由于原始通过index跳转到cart时，没有operate默认是index方法
                                                            //但是现在我们需要指明到controller调用的方法名字
                                                            operate: 'editCart',
                                                            cartItemId:cartItemId,
                                                            buyCount:buyCount
                                                        }
                                                        //我们也可以使用data以json的格式发给服务器，但是服务器需要修改

                                                    }).then(
                                                        function (value) {
                                                            vue.getCart();
                                                        }
                                                    ).catch(
                                                        function (reason) {  }
                                                    )
                                                }
                                 修改CartController的editCart
                                            原：
                                                public String  editCart(Integer cartItemId,Integer buyCount){
                                                        //session获取当前用户
                                                        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
                                                        return "redirect:cart.do";
                                                    }
                                            现在：
                                                public String  editCart(Integer cartItemId,Integer buyCount){
                                                        //session获取当前用户
                                                        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
                                                        return ""; //一般会返回状态码，这里我们不在设置
                                        }
        - 7.此时“=”和“-”已经实现，但页面的“共件 商品”和“总金额 元”未能实现，
            原因和上面的xj不能显示一样
            解决方法：
                1.修改html代码：
                    原：
                       <div>共<span th:text="${session.currUser.cart.totalBookCount}">3</span>件商品</div>
                       <div class="total-price" th:text="${session.currUser.cart.totalPrice}">总金额<span>99.9</span>元</div>

                    现在：
                        <div>共<span>{{cart.totalBookCount}}</span>件商品</div>
                        <div>总金额<span>{{cart.totalPrice}}</span>元</div>
                2.在cartController的cartInfo中调用cart的三个属性
                    public String cartInfo(HttpSession session){
                            User currUser = (User) session.getAttribute("currUser");
                            Cart cart = cartItemService.getCart(currUser);

                            cart.getTotalPrice();
                            cart.getTotalBookCount();
                            cart.getTotalCount();

                            //我们需要返回cart
                            Gson gson = new Gson();
                            String cartToJsonStr = gson.toJson(cart);
                            return "json:"+cartToJsonStr;

                        }


注意：
    VUE中关于“vue.属性”和“this.属性”的区别：
        比如,我们在methods中的axios的then部分，我们使用“vue.”;但在mounted中使用“this.”
        原因：
            1. 在then中，谁调用这个function其就是this(this代表当前的调用者)
                    function (value) {
                     var cart = value.data;
                    }
            2.在mounted中，当前的生命周期就是vue的，呢么该function就是vue对象调用的，因此使用this。
                    mounted: function () {
                        // vue.getCart(); //报错，将会说getCart()未定义，因此需要使用this
                        this.getCart();
                    }




