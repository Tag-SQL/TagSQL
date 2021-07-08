String.prototype.replaceWithObj = function(obj) {
    var retStr = this;
    for (var x in obj) {
        retStr = retStr.replace(new RegExp(x, 'g'), obj[x]);
    }
    return retStr;
};
function isStringType(obj){
    if (typeof obj === 'string' || obj instanceof String){
        return true;
    }
    return false;
}

function isEmptyOrNull(value){
    if (isStringType(value)){
           return (!value || value == undefined || value == "" || value.length == 0);
    }
    if (null == value){
        return true;
    }
    return false;
 }
var tracer ={

    debug:function(){
        if (null != arguments && arguments.length>0){
            console.println('JavaScript->trace:');
        }
        for (var i=0; i < arguments.length; i++) {
            console.print(arguments[i]);
            console.print(i + 1 < arguments.length ? ",":"\n");
        }
    }
}

var lib ={
 
    //lib.replace(@Line,'%',@{firstName})
    replace: function(){

        var argumentsArray = [].slice.apply(arguments);
        argumentsArray = ["try replace  arguments?"].concat(argumentsArray);
        tracer.debug.apply(this,argumentsArray);

        var source = arguments[0];

        if (isEmptyOrNull(source) ){
            return source;
        }
        var argumentsArray = [].slice.apply(arguments);
        var keyValues = argumentsArray.slice(1,arguments.length);

        if (null ==keyValues || keyValues.length<=0){
            return source;
        }
        var result = "";

        if (keyValues.length == 1){ return "" }
        //@case key:value pair
        if (keyValues.length == 2){
            var key = keyValues[0];
            var value = keyValues[1];
            if (isEmptyOrNull(value)){
                 result =  "";
            }else{
                 result = source.replace(key,value);
            }

            console.log("result=["+ result +"]" );
            return result;
        }
        //@case many key:value
        return this._replaceMany(source,keyValues);
    },

    _replaceMany:function(source,keyValues){
        //@case multi key value pair
        var result = source;
        var params ={};
        for (var i=0; i < keyValues.length; i++) {
            var key = "\'"+ keyValues[i] +"\'";
            var value = keyValues[i+1];
            if (isEmptyOrNull(value)){
                result ="";
                console.println("one param is null then,result=["+ result +"]" );
                return  result;
            }
            if (isStringType(value) && !isEmptyOrNull(value)){
                value = "\'"+ value +"\'";
            }
            params[key] = value;

            i = i + 1;

        }
        result  = result.replaceWithObj(params);
        console.println("result=["+ result +"]" );
        return result;
    }
}
