// function $(id) {
//     var elementById = document.getElementById(id);
//     return elementById;
// }

function $(id){
    return document.getElementById(id);
}

function preRegist(){
    //1.验证uname
    var unameTxt = $("unameTxt");
    var uname = unameTxt.value;
    //uname不能为空，用户名应为6~16位数组和字母组成
    var regUname=/[0-9a-zA-Z]{6,16}/;
    var flag = regUname.test(uname);
    var unameSpan = $("unameSpan");
    if(!flag){
        //如果不满足正则，则就显示span(修改visibility属性: hidden;)

        unameSpan.style.visibility = "visible";
        return false; //阻止表单提交
    }else{
        unameSpan.style.visibility = "hidden";
    }

    //2.验证密码
    var pwdTxt = $("pwdTxt");
    var pwd = pwdTxt.value;
    var regPwd = /[\w]{8,}/;
    //密码的长度至少为8位
    var pwdSpan = $("pwdSpan");
    if(!regPwd.test(pwd)){
        //长度《8则显示错误信息
        pwdSpan.style.visibility = "visible";
        return false; //阻止表单提交
    }else{
        pwdSpan.style.visibility = "hidden";
    }

    //3.重新输入密码
    var pwdTxt2 = $("pwdTxt2");
    var pwd2 = pwdTxt2.value;
    //密码的长度至少为8位
    var pwdSpan2 = $("pwdSpan2");
    if(pwd2!=pwd){
        pwdSpan2.style.visibility = "visible";
        return false; //阻止表单提交
    }else{
        pwdSpan2.style.visibility = "hidden";
    }

    //4.请输入正确的email
    var emailTxt = $("emailTxt");
    var email = emailTxt.value;
    var regEmail = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    //密码的长度至少为8位
    var emailSpan = $("emailSpan");
    if(!regEmail.test(email)){
        //长度《8则显示错误信息
        emailSpan.style.visibility = "visible";
        return false; //阻止表单提交
    }else{
        emailSpan.style.visibility = "hidden";
    }

    //
}


var xmlHttpRequest;
function createXMLHttpRequest() {
    if(window.XMLHttpRequest){
        //符合标准的DOM2浏览器的xmlHttpRequest创建方式
        xmlHttpRequest= new XMLHttpRequest()
    }else{
        //ie浏览器
        try{
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP")
        }
    }
}

function ckUname(uname) {
    //如果想要发送异步请求，我们必须需要一个
    createXMLHttpRequest();
    var url = "user.do?operate=ckUname&uname="+uname ;
    xmlHttpRequest.open("GET",url,true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB ;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        //xmlHttpRequest.responseText 表示 服务器端响应给我的文本内容
        //alert(xmlHttpRequest.responseText);
        var responseText = xmlHttpRequest.responseText ;
        // {'uname':'1'}
        //alert(responseText);
        if(responseText=="{'uname':'1'}"){
            alert("用户名已经被注册！");
        }else{
            alert("用户名可以注册！");
        }
    }
}