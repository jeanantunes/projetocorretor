function getPropertie(key, callback){

    $.ajax({
        async: true,
        url: "/propertie?key=" + key,
        method: "GET",
        success: function (resp) {
            callback(resp[key]);
        },
        error: function (xhr) {
            callback(xhr);
        }
    });

}