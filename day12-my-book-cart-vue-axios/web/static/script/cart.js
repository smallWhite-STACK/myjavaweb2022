window.onload = function () {

    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart:{},
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    // data:{
                    //
                    // },
                    params: {
                        //由于原始通过index跳转到cart时，没有operate默认是index方法
                        //但是现在我们需要指明到controller调用的方法名字
                        operate: 'cartInfo'
                    }
                }).then(
                    function (value) {
                        var cart = value.data;
                        vue.cart=cart;
                    }
                ).catch(function (reason) {

                });
            },
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
        },
        mounted: function () {
            // vue.getCart();
            this.getCart();
        }
    });
}