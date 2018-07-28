app.factory("Constant",function ($location) {
    var SERVICE_URL_PRODUCTION = "http://"+$location.host()+":8080/lexicon/";
    return {
        ApiPath:SERVICE_URL_PRODUCTION,
    };
});