1.Vue来说，导入Vue的外部js文件就能够使用Vue框架了。
2.Vue简单示例：
//使用vue操作span标签的文本值


        <div id="div0">
            <span>{{msg}}</span>
        </div>
        js:
        window.onload=function(){
            var vue = new Vue({
                "el":"#div0",   //el用于指定Vue对象要关联的HTML元素。el就是element的缩写
                data:{          // data属性设置了Vue对象中保存的数据
                    msg:"hello vue!"
                }
            });
        }


3.v-bind(可以省略)
<!-- v-bind:value表示绑定value属性 , v-bind可以省略，也就是 :value -->
        <input type="text" v-bind:value="uname" />
        <input type="text" :value="uname" />

4.v-model
    1.v-model:value
         <!--
            v-model指的是双向绑定，
            也就是说之前的v-bind是通过msg这个变量的值来控制input输入框
            现在 v-model 不仅msg来控制input输入框，反过来，input输入框的内容也会改变msg的值
        -->
                <script language="JavaScript" src="script/vue.js"></script>
                    <script language="JavaScript">
                        //使用vue操作span标签的文本值
                        window.onload=function(){
                            var vue = new Vue({
                                "el":"#div0",  //el用于指定Vue对象要关联的HTML元素。el就是element的缩写
                                data:{          // data属性设置了Vue对象中保存的数据
                                    msg:"hello vue!"
                                }
                            });
                        }
                    </script>
                </head>

                <body>
                    <div id="div0">
                        <!--<span>Hello</span>-->
                        <span>{{msg}}</span>
                        <input type="text" v-model:value="msg" />
                    </div>
    2.v-model
        <!-- v-model:value 中 :value可以省略，直接写成v-model -->

    3.v-model.trim
        <!-- trim可以去除首尾空格 -->

5.v-if v-else:条件渲染
 1.v-if和v-else之间不可以插入其他节点
 2.v-show
   v-show是通过display属性来控制这个div是否显示

6.v-for列表渲染

7.事件驱动
 1.字符串顺序反转
 注意是v-on:click不是v-on:onclick
 <!-- v-on:click 表示绑定点击事件 -->
 <!-- v-on可以省略，变成 @click -->

            <div id="div0">
                <!--实现对一个span内的字符串进行翻转-->
                <span>{{message}}}</span>
                <input type="button" value="反转span字符串" v-on:click="reverseMy" />
            </div>

            window.onload=function(){
                    var vue = new Vue({
                        "el":"#div0",  //el用于指定Vue对象要关联的HTML元素。el就是element的缩写
                        data:{          // data属性设置了Vue对象中保存的数据
                            message:"123456789"
                        },
                        methods:{
                            reverseMy:function () {
                                this.message=this.message.split("").reverse().join("");

                            }

                        }
                    });
                }

 2.获取鼠标移动时的坐标信息
            window.onload=function(){
                 var vue = new Vue({
                     "el":"#app",
                     data:{          // data属性设置了Vue对象中保存的数据
                         position:"暂时没有获取到鼠标的位置信息"
                     },
                     methods:{
                         "recordPosition":function(event){
                             this.position = event.clientX + " " + event.clientY;
                         }
                     }
                 });
             }

            <div id="app">
                 <div id="area" style="height: 100px;width: 100px" @mousemove="recordPosition"></div>
                 <p id="showPosition">{{position}}</p>
             </div>
8.侦听属性
                window.onload=function(){
                    var vue = new Vue({
                        "el":"#app",
                        data:{
                            "leftVal":1,
                            "rightVal":2,
                            "sumVal":0
                        },
                        //我们需要监听leftVal和rightVal
                        watch:{
                            "leftVal":function (newVal) {
                                this.sumVal=parseInt(newVal)+parseInt(this.rightVal);
                            },
                            "rightVal":function (newVal) {
                                this.sumVal=parseInt(newVal)+parseInt(this.leftVal);
                            }

                        }
                    });

                <div id="app">
                   <input type="text" v-model:value="leftVal" size="2"/>+
                    <input type="text" v-model:value="rightVal" size="2"/>=
                    <span>{{sumVal}}</span>
                </div>

9.Vue对象生命周期
一个对象从创建、初始化、工作再到释放、清理和销毁，会经历很多环节的演变
    1.状态
         1）.beforeCreate
         2）.created
         3）.beforeMount
         4）.mounted
         5）.beforeUpdate
         6）.updated
         7）.beforeDestory
         8）.destoryed
    2.生命周期钩子函数
        Vue允许我们在特定的生命周期环节中通过钩子函数来加入我们的代码。

            <div id="div0">
                <span id="span">{{msg}}</span><br/>
                <input type="button" value="改变msg的值" @click="changeMsg" />
            </div>

        window.onload=function(){
                    var vue = new Vue({
                        "el":"#div0",

                        data:{
                            "msg":1,
                        },

                        //注意：methods不是methods
                        methods: {
                            changeMsg: function () {
                                this.msg = this.msg + 1;
                            },
                        },

                        /*vue对象创建之前*/
                        beforeCreate:function () {
                            console.log("beforeCreate:vue对象创建之前---------------");
                            console.log("msg:"+this.msg); //msg:undefined
                        },
                        /*vue对象创建之后*/
                        created:function () {
                            console.log("created:vue对象创建之后---------------");
                            console.log("msg:"+this.msg); //msg:1
                        },
                        /*数据装载之前*/
                        beforeMount:function () {
                            console.log("beforeMount:数据装载之前---------------");
                            console.log("span:"+document.getElementById("span").innerText); //span:{{msg}}
                        },
                        /*数据装载之后*/
                        mounted:function () {
                            console.log("mounted:数据装载之后---------------");
                            console.log("span:"+document.getElementById("span").innerText); //span:1
                        },

                        beforeUpdate:function(){
                            console.log("beforeUpdate:数据更新之前---------------");
                            console.log("msg:"+this.msg);   //msg:2
                            console.log("span:"+document.getElementById("span").innerText);//span:1
                        },
                        updated:function(){
                            console.log("Updated:数据更新之后---------------");
                            console.log("msg:"+this.msg);  //msg:2
                            console.log("span:"+document.getElementById("span").innerText);//span:2
                        }


                    });






