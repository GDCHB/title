app.factory("titleModel",function ($resource,Constant) {

    return $resource(Constant.ApiPath + ':_1',{_1:'@_1',_2:'@_2',_3:'@_3',_4:'@_4'},
        {
            'getTitleList':{
                method:"get"
            },
        });
});
app.factory('titleReqModel',function (Constant) {
    var taskRequest = {
        getUserId:function(){
            var result =null;
            $.ajax({
                method: "POST",
                async: false,
                url: 'getUserId',
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    result=data;
                },
                error:function (err) {
                    console.log(err.msg);
                    result =-1;
                }
            });
            return result;
        },
        saveLexicon:function (lexicon) {
            var postData={lexicon:JSON.stringify(lexicon)};
            var result = null;
            $.ajax({
                method: "POST",
                async: false,
                url: 'save',
                dataType: 'json',
                data:postData,
                success: function (data) {
                    console.log(data);
                    result=data;
                },
                error:function (err) {
                    console.log(err.msg);
                    result = -1;
                }
            });
            return result;
        },
        getData:function(dataInfo,actionName){
            var dataPost = dataInfo;
            var result =null;
            $.ajax({
                method: "POST",
                async: false,
                url : actionName,
                dataType: 'json',
                data:dataPost,
                success: function (data) {
                    console.log(data);
                    if(data.flag == "1"){
                        result=data.result;
                    }else{
                        console.log(data.msg);
                        result = -1;
                    }
                },
                error:function (err) {
                    console.log(err.msg);
                    result = -1;
                }
            });
            return result;
        },
        saveData:function (dataInfo,actionName) {
            var dataPost = dataInfo;
            var reqResult = -1;
            $.ajax({
                method : 'POST',
                async : false,
                url : actionName,
                dataType : 'json',
                data:dataPost,
                success : function(data){
                    console.log(data);
                    if(data.flag == "1"){
                        reqResult = 1;
                    }else {
                        console.log(data.msg);
                        reqResult = -1;
                    }

                },
                error:function(err){
                    console.log(err.msg);
                    reqResult = -1;
                }
            });
            return reqResult;
        },
    };
    return taskRequest;
});