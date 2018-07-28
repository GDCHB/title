app.directive('kkPager',['titleSrv', function (titleSrv) {
    return {
        restrict: 'E',
        template: '<div id="kkpager"></div>',
        replace: true,
        scope:{},
        link:function(scope,ele){
            var pageNum = titleSrv.getPageNum();
            var pages = titleSrv.getPages();
            var total = titleSrv.getTotal();
            //生成分页控件
            kkpager.generPageHtml({
                pno : pageNum,
                mode : 'click', //可选，默认就是link
                //总页码
                total : pages,
                //总数据条数
                totalRecords : total,
                click : function(page){
                    //这里可以做自已的处理
                    //处理完后可以手动条用selectPage进行页码选中切换
                    titleSrv.setPageNum(page);
                    titleSrv.loadTitleList();
                    this.selectPage(page);
                },
                //getHref是在click模式下链接算法，一般不需要配置，默认代码如下
                getHref : function(n){
                    return '#';
                }
            },true);
        }
    };
}])