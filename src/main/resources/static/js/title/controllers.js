app.controller("titleCtrl",['$scope','$rootScope','$timeout','titleSrv','$compile',function ($scope,$rootScope,$timeout,titleSrv,$compile) {

    $scope.titleList = [];
    $scope.getTitleList = function () {
        titleSrv.loadTitleList($scope.setTitleList);
    };
    $scope.setTitleList = function (list) {
        $scope.titleList = list;
        angular.element("#titleListPage").empty();
        angular.element("#titleListPage").append("<kk-pager></kk-pager>");
        $compile(angular.element("#titleListPage"))($scope);
    };
    titleSrv.registerCallBack($scope.setTitleList,"setTitleList");
    $scope.getTitleList();

    $scope.newLexicon = {
        content:"",
        lexiconNum:"1"
    };
    $scope.newContent = function () {
        var addTitleDialog = $("#addTitle").dialog({
            autoOpen: true,
            height: 215,
            width: 626,
            modal: true,
            title: "新增内容",
            buttons: {
                "确定": function () {
                    var ret = titleSrv.saveLexicon($scope.newLexicon);
                    addTitleDialog.dialog("close");
                },
                "取消": function () {
                    addTitleDialog.dialog("close");
                }
            },
            close: function () {
                addTitleDialog.dialog("close");
            }
        });
    };
    $scope.buildTitleRet = "新生成的标题";
    $scope.buildTitle = function(){
        $scope.buildTitleRet = titleSrv.buildTitle();
        var buildTitleDialog = $("#buildTitle").dialog({
            autoOpen: true,
            height: 215,
            width: 626,
            modal: true,
            title: "新增内容",
            buttons: {
                "确定": function () {
                    buildTitleDialog.dialog("close");
                },

            },
            close: function () {
                buildTitleDialog.dialog("close");
            }
        });
    }
    $scope.delLexicon = function(id){
        var ret = titleSrv.delLexicon(id);
        if(ret == 1){
            alert("删除成功");
            titleSrv.loadTitleList();
        }else {
            alert("删除失败");
        }
    };
}]);