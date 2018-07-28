app.factory("titleSrv",['$rootScope','$q','titleModel','titleReqModel',function ($rootScope,$q,titleModel,titleReqModel) {
    //回调序列
    var m_callBackFun = [];

    var pageNum=1 ;
    var pageSize = 15;
    var pages = 1;
    var total = 1;
    //当前用户
    var m_CurrUserId= "";
    var getTitleList = function (pageNum,pageSize) {
        var deferred=$q.defer();
        titleModel.getTitleList({pageNum:pageNum,pageSize:pageSize},{_1:"get"}, function(json) {
            deferred.resolve(json);
        },function(fail){
            deferred.reject(fail);
        });
        return deferred.promise;
    };

    var taskSrvObject={
        getPageNum : function () {
            return pageNum;
        },
        setPageNum : function(param){
            pageNum = param
        },
        getPages : function () {
            return pages
        },
        setPageS : function(param) {
            pages = param;
        },
        getTotal : function () {
            return total;
        },
        setTotal : function (param) {
            total = param;
        },
        getPageSize : function(){
            return pageSize
        },
        setPageSize : function(param){
            pageSize = param;
        },
        getUserId:function(){
            var context = titleReqModel.getUserId();
            if( context == null || context == "" || context == "undefined" || context == -1){
                m_CurrUserId = "";
            }else if(context.flag == 1){
                m_CurrUserId = context.userId;
            }
            return m_CurrUserId;
        },
        /**
         * 获取内容列表
         * @param callBack
         */
        loadTitleList:function(callBack){
            getTitleList(pageNum,pageSize).then(
                function(data){
                    console.log("loadTitleList succ")
                    console.log(data);
                    var result = [];
                    if(data.flag == 1){
                        result = data.result;
                        pages = result.pages;
                        pageNum = result.pageNum;
                        total = result.total;
                    }else{
                        console.log("loadTaskList fail"+data.msg);
                    }
                    if(callBack == undefined){
                        taskSrvObject.notifyCallBack("setTitleList",result.list);
                    }else {
                        callBack(result.list);
                    }



                },
                function(errMsg){
                    console.log("loadTaskList fail"+errMsg);
                })
        },
        saveLexicon:function(lexicon){
            var param = {
                contents:lexicon.content,
                lexiconNum:lexicon.lexiconNum
            };
            return titleReqModel.saveData(param,"save");
        },
        buildTitle:function(){
            return titleReqModel.getData("","buildTitle");
        },
        delLexicon:function(id){
            var param ={
                id:id
            }
            return titleReqModel.saveData(param,"del");
        },

        registerCallBack: function(callback,callname) {
               var flag = true;
               angular.forEach(m_callBackFun, function(item) {
                   if(item.name == callname){
                       flag = false;
                   }
               });
               if(flag) {
                   var item = {
                       name:callname,
                       func:callback
                   };
                   m_callBackFun.push(item);
                   console.log('registerCallBack :'+callname);
               }
           },
        notifyCallBack: function(callname,params) {
               angular.forEach(m_callBackFun, function(item) {
                   if(item.name == callname){
                       if(params !=null){
                           item.func(params);
                       }else {
                           item.func();
                       }
                   }
               });
        }
   };
   return taskSrvObject;
}]);