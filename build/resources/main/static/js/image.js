
$(function() {
    var params = {
        // Request parameters
        "application": "{string}",
    };

    const urlParams = new URL(location.href).searchParams;

    const name = urlParams.get('image');

    const serverURL = "http://topen0330.kro.kr/"

    $.ajax({
        url: "https://topen0330cv.cognitiveservices.azure.com/customvision/v3.0/Prediction/37744c03-6db3-47a3-9881-840d0bb21ac8/classify/iterations/Iteration3/url?" + $.param(params),
        beforeSend: function(xhrObj){
            // Request headers
            xhrObj.setRequestHeader("Content-Type","application/json");
            xhrObj.setRequestHeader("Prediction-key","57daae739b8f476b86264a0a45131e80");
        },
        type: "POST",
        // Request body
        data: '{"Url": "'+serverURL+'image/'+name+'"}',
    })
        .done(function(data) {
            console.log(data);
        })
        .fail(function() {
            console.log("failed")
        });
});