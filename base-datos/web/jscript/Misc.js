getparams = function(){
    idx = document.URL.indexOf('?', 0);
    tempParams = new Object();
    if(idx != -1){
        pairs = document.URL.substring(idx + 1, document.URL.length).split('&');
        for(var i = 0; i < pairs.length; i++){
            var nameVal = pairs[i].split('=');
            tempParams[nameVal[0]] = nameVal[1];
        }
    }
    return tempParams;
};