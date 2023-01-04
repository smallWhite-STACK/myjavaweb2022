1. 我们将`static文件夹`放在web内部，和`文件夹pages(`index.html页面`在pages内部)`放在WEB-INF不让随意访问
    1)注意：原来我们是将index.html放在web下（与web-inf同级）
    2)因此我们需要修改web.xml的前缀的value
    - 原：
        <context-param>
            <param-name>view-prefix</param-name>
            <param-value>/</param-value>
        </context-param>

    - 现：
        <context-param>
            <param-name>view-prefix</param-name>
            <param-value>/WEB-INF/pages/</param-value>
        </context-param>

2. myssm.jar包以及jdbc.properties和web.xml
    1)其中pages是我的，pages-teach是现成的

3.清理一下applicationContext.xml内的标签
<!-- DAO、SERVICE、CONTROLLER -->

4.设置tocmat
    这里我们设置一下初始路径:
        根据“pages/user/login.html”
        "xjbook是自己定义的"
        "因为我们在web.xml中设置了前缀为/WEB-INF/pages，所以这里page值为：user/login即可完成拼接"
        "其中operate=page这个page则通过中心处理器去pageController（这个controller则是通过page.do找到的）中调用page方法"
        http://localhost:8080/xjbook/page.do?operate=page&page=user/login
        http://localhost:8080/xjbook/page.do?operate=page&page=user/login

5.因此我们从/WEB-INF/pages/user/login.html开始修改
    1)主要是加入thymeleaf语法:  @{} ${}
        src引用路径
        form表单的action（xxx.do）
            在form下加上一个隐藏域name=operate
                <form th:action="@{/user.do}" method="post">
                    <input type="hidden" name="operate" value="login"/>
6.在IDEA中如果出现奇怪的错误，我们可以重建构建-项目
7.我们userController中转向："redirect:book.do"（这里省略了"?operate=index"）(因为我们在DispatcherServlet中设置了默认值)
    因此我们需要在bookController中通过补充session中的bookList,然后返回到index页面

8.在index页面上展示我们的bookList数据
   1)修改index.html页面
   2)如果页面上出现一个bug显示不理想，一般都是thymeleaf的问题（注意"th:"不能少）
            <div class="list-content">
              <!--4.遍历-->
              <!--4.1  th:object="${book}"是为了下面使用book这个变量时不使用 "book." 的方式-->
              <div href="" class="list-item" th:each="book:${session.bookList}" th:object="${book}">
                <!--4.2 修改每一个book对应的img的src-->
                  <!--正是因为上面的th:object所以下面我们可以直接写bookImg-->
                <img th:src="@{|/static/uploads/*{bookImg}|}" alt="">
                <!--4.3书名等（记得前面的“书名”等字符串）-->
                <p th:text="|书名2:*{bookName}|">书名:活着</p>
                <p th:text="|作者:*{author}|">作者:余华</p>
                <p th:text="|价格:￥*{price}|">价格:￥66.6</p>
                <p th:text="|销量:*{saleCount}|">销量:230</p>
                <p th:text="|库存:*{bookCount}|">库存:1000</p>
                <button>加入购物车</button>
              </div>
              <!--3.删除其他的div，我们在上面遍历bookList即可-->
            </div>
9.加入购物车功能（中间设计用户是否登录（显示不同的页面元素））

    1)判断是否登录(th:if和th:unless)
        主要是通过session是是否有currUser，如果有则登陆成功，否则登录失败

    2)购物车的功能（点击具体图书添加到购物车中）
        1、按钮"加入购物车"的点击事件
        2、设置CartItemController等（设置Cart购物车类、DAO、Service等）
        3、设置index页面上购物车上的小标
        4、注意：在数据库中的列对应的是java类属性的属性

    3)查看用户的购物车页面cart.do
        1.Caused by: java.lang.NullPointerException
          	at com.atguigu.book.pojo.Cart.getTotalPrice(Cart.java:36)
          	改原因是在cart中计算totalPrice时，由于cart中的cartItem的book只有id，price为空导致的（CartDAO）
          	因此我们需要在CartItemService中写一个getCartItemList(User user)的方法

          	- 原来我们在cartService的getCart中使用的CartDAO的getCatItemList方法，但是由于这个空指针的原因，我们在service中对
          	- CartDAO的getCatItemList获取的结果进一步封装，然后完善book的属性，从而解决了空指针的问题
          	- 并且在此过程中，我们需要使用bookService（因为想要使用bookid获取完整的book信息，这里还需要去在
          	- bookService中写一个通过bookid获取的book所有信息的方法(由于Dao中也没有通过id获取，所以需要完善DAO)）
    4)结账功能的实现（OrderServiceImpl的addOrderBean）
        1.订单表先添加一条记录
            OrderDAO(addOrderBean(OrderBean))
                    在实现addOrderBean(OrderBean)时我们需要接收update返回的值（设置为oderBean的id）
                        原因：因为参数OrderBean是一个待插入的order,插入后获得了id，此时我们将返回的id设置为这个order的id，之后可以使用
            OrderItemDAO(addOrderItem(OrderItem))
            CartItemDAO(delCartItem(CartItem))

            service层
                OrderService(addOrderBean(OrderBean))
        2.订单详情表添加7条记录
            在2中非常重要的是：（OrderServiceImpl的addOrderBean）
                //由于使用orderBean.getOrderItemList()获取的orderItem是null,
                //因此此处我们应该根据用户的购物车项去转换一个一个的订单项

        3.购物车项中删除原来的7条记录


10.我的订单

11.cart的+和-
                <span class="count">-</span>
                <input class="count-num" type="text" value="1" th:value="${cartItem.buyCount}"/>
                <span class="count">+</span>
12.关于计算精度问题bigDecimal
cart.html的
    1. <td th:text="${cartItem.book.price * cartItem.buyCount}">36.8</td>
        解决问题1：在CartItem中增加一个小计double xj属性
                    在getXj中将book的price和cartItem的buyCount修改为BigDecimal
                    然后调用bigDecimal的乘法操作，最后将乘法的结果转换为double类型
                    从而设置属性xj，html这里可以修改为${cartItem.xj}

                        public double getXj() {
                                BigDecimal price = new BigDecimal(getBook().getPrice() + "");
                                BigDecimal count = new BigDecimal(buyCount + "");
                                BigDecimal bigDecimalXj = price.multiply(count);
                                double xj = bigDecimalXj.doubleValue();
                                return this.xj;
                                    }
    <div class="total-price" th:text="${session.currUser.cart.totalPrice}">总金额<span>99.9</span>元</div>
13.过滤器（com\atguigu\z_book\filters\SessionFilter.java）
在我们写的controller中我们凑会从session中获取currUser，但是如果如果当没有登录时就会出现很多问题（非法用户）
因此我们加入一个过滤器：
    首先，判断session中有无user，有就放行（没有就重定向到login）
    (因此过滤器中的白名单应该是：登录、注册)
    注意：由于我们之前的com.atguigu.myssm中是m开头的，而我们的com.atguigu.book是b开头的
            而且我们所有的都是使用注解的形式，并没有在web.xml中配置，因此
            如果在book创建过滤器则会让myssm的CharacterEncoding失效
    因此我们创建一个新的包，开头的字母必须在m之后

    关于设置的filter的WebFilter的注解的设置
        register.html
        login.html
        user.do?operate=login
        user.do?operate=regist
        只有上面四种情况，过滤器才会放行
    1、问题：当访问user.do?operate=page&page=login时提示“重定向次数过多”
           分析：因为过滤器未放行
           request.getRequestURL()  -->http://localhost:8080/xjbook/page.do
           和
           request.getRequestURI()  -->/xjbook/page.do
           和
           request.getQueryString()  -->operate=page&page=login

        解决方式1：通过设置@WebFilter的initParams
            @WebFilter(urlPatterns = {"*.do","*.html"},
                        initParams = {@WebInitParam(name = "bai",
                                                  value= "/xjbook/page.do?operate=page&page=user/login,/xjbook/user.do?null")}
            )
                                    //value中将会有多个字符串，以“，”分隔，作为白名单对象
                                    ///xjbook/user.do等同于/xjbook/user.do?null(如果不想写null，则在下方进行字符串拼接时做一个判断，如果为null就不拼接)

        解决方式2：不需要设置过滤器，我们只需在各个controller中session.get用户对象时，判断一下是否为空，若为空则直接重定向到login页面即可

14.cookie
   1. 创建Cookie对象
   2. 在客户端保存Cookie
   3. 设置Cookie的有效时长
      cookie.setMaxAge(60)  ， 设置cookie的有效时长是60秒
      cookie.setDomain(pattern);
      cookie.setPath(uri);
   4. Cookie的应用：
     4-1: 记住用户名和密码十天 setMaxAge(60 * 60 * 24 * 10)
     4-2: 十天免登录

15. Kaptcha
   1. 为什么需要验证码（防止恶意攻击）
   2. kaptcha如何使用:
      - 1）添加jar
      - 2）在web.xml文件中注册KaptchaServlet，并设置验证码图片的相关属性
            - 注册：
                <servlet>
                    <servlet-name>KaptchaServlet</servlet-name>
                    <servlet-class>com.google.code.kaptcha.servlet.KaptchaServlet</servlet-class>
                </servlet>
                <servlet-mapping>
                    <servlet-name>KaptchaServlet</servlet-name>
                    <url-pattern>/kaptcha.jpg</url-pattern>
                </servlet-mapping>
            - 设置验证码图片的相关属性（我们可以去jar包下的Constants类看一些关于验证码的属性常量）
                    https://blog.csdn.net/qq_37591656/article/details/83831496(kaptcha中的各种属性)
                         <init-param>
                            <!--param-name就是constants中的常量-->
                            <param-name>kaptcha.border.color</param-name>
                            <param-value>red</param-value>
                        </init-param>
                        <init-param>
                            <param-name>kaptcha.textproducer.char.string</param-name>  <!--验证码上出现的字符的可选范围-->
                            <param-value>abcdefgh</param-value>
                        </init-param>
      - 3）在html页面上编写一个img标签，然后设置src等于KaptchaServlet对应的url-pattern
   3. kaptcha验证码图片的各个属性在常量接口：Constants中
   4. KaptchaServlet在生成验证码图片时，会同时将验证码信息保存到session中
      因此，我们在注册请求时，首先将用户文本框中输入的验证码值和session中保存的值进行比较（key是Constants常量），相等，则进行注册

15.2 实现book注册
        1.修改tocmat默认访问页面
        http://localhost:8080/xjbook/page.do?operate=page&page=user/login
        http://localhost:8080/xjbook/page.do?operate=page&page=user/regist
        2.修改day09-my-book\web\WEB-INF\pages\user\regist.html的thyleafme的语法(包括在xml中配置)
        3.设置Service和DAO的User相关操作


16. JS - Exp
   1)正则表达式的使用三步骤：
       1. 定义正则表达式对象
          正则表达式定义有两个方式：
          1) 对象形式
             var reg = new RegExp("abc")
          2) 直接量形式  : /正则表达式/
             var reg = /abc/;

          3) 匹配模式：
           - g 全局匹配
           - i 忽略大小写匹配
           - m 多行匹配
           - gim这三个可以组合使用，不区分先后顺序
             例如：
                    var reg = /abc/gim ,
                    var reg = new RegExp("abc","gim");

       2. 定义待校验的字符串
       3. 校验


   2)元字符
     . , \w , \W , \s , \S , \d , \D , \b , ^ , $

   3)[]表示集合
     [abc] 表示 a或者b或者c
     [^abc] 表示取反，只要不是a不是b不是c就匹配
     [a-c] 表示a到c这个范围匹配

   4) 出现的次数
     * 表示多次 （0 ~ n ）
     + 至少一次 ( >=1 )
     ? 最多一次 (0 ~ 1)
     {n} 出现n次
     {n,} 出现n次或者多次
     {n,m} 出现n到m次
17.注册时用户输入的个人信息进行表单验证（需要JS正则）
    1.form表单的onsubmit事件
        onsubmit="return fasle",呢么表单点击提交时按钮不会提交
        onsubmit="return true" ,呢么表单点击提交时按钮会提交

        因此：我们可以设置onsubmit="return preRegist();"
        写一个js的preRegist方法

    2.获取文档中没有一个节点（标签）的方式
     方式1：DOM （document.getElementById("unameText")）  //unameText是标签的id
     方式2：BOM  （document.forms[0].uname）              //uname是标签的name属性